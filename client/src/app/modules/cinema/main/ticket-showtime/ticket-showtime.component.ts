import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from 'src/app/models/project';
import { DateService } from 'src/app/services/date.service';
import { OrderService } from 'src/app/services/order.service';
import { ShowtimeService } from 'src/app/services/showtime.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-ticket-showtime',
  templateUrl: './ticket-showtime.component.html',
  styleUrls: ['./ticket-showtime.component.scss'],
})
export class TicketShowtimeComponent {
  imageUrl = environment.BASE_URL;
  showTimeMovie: any;
  selectedDate: any;
  constructor(
    private dateSer: DateService,
    private router: Router,
    public orderSer: OrderService,
    private showtimeSer: ShowtimeService
  ) {
    this.showtimeSer
      .getShowtimesByFilm(this.orderSer.getTicketData().movie.id)
      .subscribe((res) => {
        this.showTimeMovie = this.dateSer.groupShowtimesByDate(res.data);
      });
  }

  orderTicket(showtime: any) {
    this.orderSer.setMovie(this.orderSer.getTicketData().movie);
    this.orderSer.setShowtime({
      id: showtime.id,
      startShow: showtime.startShow,
      screen: showtime.screen,
      date: showtime.date,
    });
    this.router.navigate(['/ticket/selectTicket']);
  }

  openDate(show: any) {
    this.selectedDate = show;
  }
}
