import { Component, model } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SelectModule } from 'primeng/select';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-country-select',
  imports: [FormsModule, SelectModule, ButtonModule],
  templateUrl: './country-select.component.html',
  styleUrl: './country-select.component.css'
})
export class CountrySelectComponent {
  selectedCountry = model('')

  // Flags from https://en.wikipedia.org/wiki/ISO_3166-1
  countries = new Map([
    ['FR', {
      name: 'France',
      icon: "https://upload.wikimedia.org/wikipedia/en/thumb/c/c3/Flag_of_France.svg/60px-Flag_of_France.svg.png"
    }],
    ['DE', {
      name: 'Germany',
      icon: "https://upload.wikimedia.org/wikipedia/en/thumb/b/ba/Flag_of_Germany.svg/60px-Flag_of_Germany.svg.png"
    }],
    ['IN', {
      name: 'India',
      icon: "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/60px-Flag_of_India.svg.png"
    }],
    ['US', {
      name: 'United States',
      icon: "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/60px-Flag_of_India.svg.png"
    }]
  ])

  public allCountries() {
    return Array.from(this.countries.keys())
  }
};

