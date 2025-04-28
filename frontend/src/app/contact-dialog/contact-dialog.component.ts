import { Component, EventEmitter, model, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { Contact } from '../contact';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { CountrySelectComponent } from '../country-select/country-select.component';

@Component({
  selector: 'app-contact-dialog',
  imports: [DialogModule, ButtonModule, InputTextModule, FormsModule, FloatLabelModule, CountrySelectComponent],
  templateUrl: './contact-dialog.component.html',
  styleUrl: './contact-dialog.component.css'
})
export class ContactDialogComponent {
  contact = model<Contact | null>(null)
  @Output() save = new EventEmitter<any>()
}
