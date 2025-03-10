import { Component } from '@angular/core';

import { DateInfo, ticket } from 'src/app/models/project';
import { OrderService } from 'src/app/services/order.service';
import { DateService } from 'src/app/services/date.service';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.scss'],
})
export class ConfirmComponent {
  dateFomatMovie!: DateInfo;
  dataTicket = new Map<
    string,
    { type: string; quantity: number; price: number }
  >();

  constructor(private dateSer: DateService, public orderSer: OrderService) {
    const date = this.orderSer.getTicketData().showtime.date.split('-');
    const d = new Date(date[0], date[1], date[2]);
    this.dateFomatMovie = {
      day: d.getDate(),
      month: d.getMonth().toString(),
      dayOfWeek: this.dateSer.getDayOfWeek(d),
    };

    this.orderSer.getTicketData().tickets.map((e: ticket) => {
      this.dataTicket.has(e.type!)
        ? this.dataTicket.set(e.type!, {
            type: e.type!,
            quantity: 1 + 1,
            price: e.price!,
          })
        : this.dataTicket.set(e.type!, {
            type: e.type!,
            quantity: 1,
            price: e.price!,
          });
    });
  }

  pay(): void {
    this.orderSer.processCardPayment().subscribe((e) => {
      window.location.href = e.data;
    });
  }
}
