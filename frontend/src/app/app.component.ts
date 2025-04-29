import { model, OnInit, signal } from '@angular/core';
import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { Contact } from './contact';
import { ContactDialogComponent } from "./contact-dialog/contact-dialog.component";
import { HttpClient } from '@angular/common/http';
import { QrCodeComponent } from './qr-code/qr-code.component';
import { CardDialogComponent } from './card-dialog/card-dialog.component';

@Component({
  selector: 'app-root',
  imports: [ButtonModule, TableModule, ContactDialogComponent, CardDialogComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  constructor(public http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<any[]>('/contacts').subscribe(result => {
      this.contacts.set(result.map(Contact.fromJson))
    })
  }

  contacts = model<Contact[]>([])
  editedContact = signal<Contact | null>(null)
  shownContact = signal<Contact | null>(null)

  public newContact() {
    this.editedContact.set(new Contact())
  }

  public editContact(contact: Contact) {
    this.editedContact.set(contact)
  }

  public deleteContact(contact: Contact) {
    const ok = confirm(`Do you want to delete ${contact.fullName()}?`)
    if(ok) {
      this.http.delete(`/contacts/${contact.id}`).subscribe( () => {
        window.location.reload()
      })
    }
  }

  public saveContact() {
    this.http.post('/contacts', this.editedContact(), {
      headers: {
        'Content-Type': 'application/json'
      }
    }).subscribe(() => {
      alert("Saved.")
      this.editedContact.set(null)
    })
  }
}
