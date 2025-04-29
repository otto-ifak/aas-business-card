package com.otto.aas_business_card;

import java.io.FileNotFoundException;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

class Reference {
    static class Key {
        public String type;
        public String value;

        public Key(String type, String value) {
            this.type = type;
            this.value = value;
        }
    }

    public String type = "ExternalReference";
    public List<Key> keys = new ArrayList<Key>();
}

class SubmodelElement {
    public String idShort;
    public Reference semanticId;

    public SubmodelElement(String idShort, String semanticId) {
        this.idShort = idShort;
        this.semanticId = new Reference();
        this.semanticId.keys.add(new Reference.Key("GlobalReference", semanticId));
    }
}

class Property extends SubmodelElement {
    public String modelType = "Property";
    public String valueType = "xs:string";
    public String value;

    public Property(String idShort, String semanticId, String value) {
        super(idShort, semanticId);
        this.value = value;
    }
}

class MultiLanguageProperty extends SubmodelElement {
    static class LangString {
        public String language = "de";
        public String text;
    }

    public String modelType = "MultiLanguageProperty";
    public List<LangString> value = new ArrayList<LangString>();

    public MultiLanguageProperty(String idShort, String semanticId, String text) {
        super(idShort, semanticId);
        LangString ls = new LangString();
        ls.language = "de";
        ls.text = text;
        value.add(ls);
    }
}

class SubmodelElementCollection extends SubmodelElement {
    public String modelType = "SubmodelElementCollection";
    public List<SubmodelElement> value = new ArrayList<SubmodelElement>();

    public SubmodelElementCollection(String idShort, String semanticId) {
        super(idShort, semanticId);
    }
}

class Submodel {
    public String modelType = "Submodel";
    public String id;
    public Reference semanticId;
    public List<SubmodelElement> submodelElements = new ArrayList<SubmodelElement>();

    public Submodel(String id, String semanticId) {
        this.id = id;
        this.semanticId = new Reference();
        this.semanticId.keys.add(new Reference.Key("GlobalReference", semanticId));
    }
}

@RestController
public class SubmodelController {

    private final ContactRepository repository;
    private final String SUBMODEL_ID_PREFIX = "http://example.com/aas-business-card/";

    static class GetReferenceResponse {
        public List<Reference> result = new ArrayList<Reference>();
    }

    SubmodelController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}/submodel")
    Submodel get(@PathVariable UUID id) throws FileNotFoundException {
        Contact contact = repository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Cannot find contact " + id));

        var result = new Submodel(SUBMODEL_ID_PREFIX + id,
                "https://admin-shell.io/zvei/nameplate/1/0/ContactInformations");
        var info = new SubmodelElementCollection("ContactInformation",
                "https://admin-shell.io/zvei/nameplate/1/0/ContactInformations/ContactInformation");
        info.value.add(new MultiLanguageProperty("NationalCode", "0173-1#02-AAO134#002", contact.nationalCode));
        info.value.add(new MultiLanguageProperty("AcademicTitle", "0173-1#02-AAO209#003", contact.academicTitle));
        info.value.add(new MultiLanguageProperty("FirstName", "0173-1#02-AAO206#002", contact.firstName));
        info.value.add(new MultiLanguageProperty("NameOfContact", "0173-1#02-AAO205#002", contact.lastName));
        info.value.add(new MultiLanguageProperty("Company", "0173-1#02-AAW001#001", contact.company));
        info.value.add(new MultiLanguageProperty("Department", "0173-1#02-AAO127#003", contact.department));
        info.value.add(new MultiLanguageProperty("Street", "0173-1#02-AAO128#002", contact.street));
        info.value.add(new MultiLanguageProperty("ZipCode", "0173-1#02-AAO129#002", contact.zipCode));
        info.value.add(new MultiLanguageProperty("CityTown", "0173-1#02-AAO132#002", contact.zipCode));
        info.value.add(new Property("AddressOfAdditionalLink", "0173-1#02-AAQ326#002", contact.web));

        var email = new SubmodelElementCollection("Email", "0173-1#02-AAQ836#005");
        email.value.add(new Property("EmailAddress", "0173-1#02-AAO198#002", contact.mail));
        info.value.add(email);

        var phone = new SubmodelElementCollection("Phone","https://admin-shell.io/zvei/nameplate/1/0/ContactInformations/ContactInformation/Phone");
        phone.value.add(new MultiLanguageProperty("TelephoneNumber", "0173-1#02-AAO136#002", contact.phone));
        info.value.add(phone);

        result.submodelElements.add(info);
        return result;
    }

    @GetMapping("/{id}/submodel/$metadata")
    Submodel getMetadata(@PathVariable UUID id) throws FileNotFoundException {
        // we are lazy here, just return the whole submodel
        return get(id);
    }

    @GetMapping("/{id}/submodel/$reference")
    Reference getReference(@PathVariable UUID id) throws FileNotFoundException {
        var ref = new Reference();
        ref.keys.add(new Reference.Key("Submodel", SUBMODEL_ID_PREFIX + id));
        return ref;
    }

    @GetMapping("/{id}/description")
    String getDescription(@PathVariable UUID id) {
        return "{\"profiles\": [\"https://admin-shell.io/aas/API/3/0/SubmodelServiceSpecification/SSP-002\"]}";
    }
}
