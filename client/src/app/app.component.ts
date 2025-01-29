import { Component } from '@angular/core';

import { ClientService } from './services/client.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  constructor(private client: ClientService) {
    if (localStorage.getItem('accessToken')) {
      this.client.getProfile();
    }
  }
}
