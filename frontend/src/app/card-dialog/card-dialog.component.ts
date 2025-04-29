import { Component, computed, EventEmitter, input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { Contact } from '../contact';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { CountrySelectComponent } from '../country-select/country-select.component';
import { QrCodeComponent } from '../qr-code/qr-code.component';

@Component({
  selector: 'app-card-dialog',
  imports: [DialogModule, ButtonModule, InputTextModule, FormsModule, FloatLabelModule, QrCodeComponent],
  templateUrl: './card-dialog.component.html',
  styleUrl: './card-dialog.component.css'
})
export class CardDialogComponent {
  contact = input<Contact | null>(null)
  @Output() onClosed = new EventEmitter()

  url = computed(() => {
    return `${window.location}${this.contact()?.id}`
  })

  public openPrintDialog() {
    window.print()
  }

}
