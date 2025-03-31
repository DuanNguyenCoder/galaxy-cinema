import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from 'src/app/models/project';
import { OrderService } from 'src/app/services/order.service';

import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-ticket-item',
  templateUrl: './ticket-item.component.html',
  styleUrls: ['./ticket-item.component.scss'],
})
export class TicketItemComponent implements OnInit {
  imageUrl = environment.BASE_URL;
  @Input() dataMovie!: Movie;
  constructor(private router: Router, private orderSer: OrderService) {}

  ngOnInit(): void {}

  redirect() {
    localStorage.setItem('movie', JSON.stringify(this.dataMovie));
    this.orderSer.resetMovieOrder();
    const formattedName = this.dataMovie
      .name!.replace(/\s+/g, '-')
      .toLowerCase();
    this.router.navigate(['/movie', formattedName]);
  }
}
