package com.otto.aas_business_card;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ContactController {

    private final ContactRepository repository;

    ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/contacts")
    List<Contact> all() {
        return repository.findAll();
    }

    @PostMapping("/contacts")
    Contact newContact(@RequestBody Contact newContact) throws FileNotFoundException {
        return repository.save(newContact);
    }

    @GetMapping("/contacts/{id}")
    Contact one(@PathVariable UUID id) throws FileNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Cannot find contact " + id));
    }

    @PatchMapping("/contacts/{id}")
    Contact replaceContact(@RequestBody Contact newContact, @PathVariable UUID id) throws FileNotFoundException {
        return repository.findById(id)
                .map(contact -> {
                    contact.mail = newContact.mail;
                    return repository.save(contact);
                })
                .orElseThrow(() -> new FileNotFoundException("Cannot find contact " + id));
    }

    @DeleteMapping("/contacts/{id}")
    void deleteContact(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}