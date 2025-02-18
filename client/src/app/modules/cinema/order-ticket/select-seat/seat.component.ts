import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { DateInfo, MovieShowTime, Seat } from 'src/app/models/project';
import { OrderService } from 'src/app/services/order.service';
import { SeatService } from 'src/app/services/seat.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-seat',
  templateUrl: './seat.component.html',
  styleUrls: ['./seat.component.scss'],
})
export class SeatComponent {
  seats: Seat[] = [];
  dataMovie!: MovieShowTime;
  dataMovieShowTime!: any;
  seatOrdered: string[] = [];
  ticketsCount: number = 3;
  selectedSeatsCount: number = 0;
  rows = ['A', 'B', 'C', 'D', 'E', 'F'];
  dateFomatMovie!: DateInfo;

  constructor(
    private router: Router,
    private seatSer: SeatService,
    public orderSer: OrderService
  ) {
    this.seatSer.getSeatOrdered(this.orderSer.getTicketData().showtime.id);
    this.seats = seatSer.seats;
  }

  seatsByRow(row: string): Seat[] {
    return this.seats.filter((seat) => seat.row === row);
  }

  getSeatClass(row: string, seat: Seat) {
    if (seat.ordered) {
      return 'seat-ordered';
    }
    const seatCode = `${row}${seat.number}`;
    const selectedTicket = this.orderSer
      .getTicketData()
      .tickets.find((t: any) => t.seat === seatCode);
    if (selectedTicket) {
      return 'selected';
    }
    return 'seat-able';
  }

  selectSeat(row: string, seat: Seat) {
    if (seat.ordered) {
      return;
    }
    const seatCode = `${row}${seat.number}`;
    const existingIndex = this.orderSer
      .getTicketData()
      .tickets.findIndex((t: any) => t.seat === seatCode);

    if (existingIndex !== -1) {
      // Nếu ghế đã chọn -> Bỏ chọn
      this.orderSer.getTicketData().tickets[existingIndex].seat = undefined;
    } else {
      // Tìm vé chưa có ghế
      const ticket = this.orderSer
        .getTicketData()
        .tickets.find((t: any) => !t.seat);
      if (ticket) {
        ticket.seat = seatCode;
      }
    }
  }

  nextStep() {
    this.orderSer.stepBreadcrumb += 1;
    this.router.navigate(['/ticket/selectFood']);
  }
}
