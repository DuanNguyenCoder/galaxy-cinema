import { Component, OnInit } from '@angular/core';
import { comboFood, Movie, ticket } from 'src/app/models/project';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.scss'],
})
export class BillComponent {
  dataMovie!: Movie;
  dataComboFood: comboFood[] = [];
  dataTicket: ticket[] = [];

  constructor(public orderSer: OrderService) {
    this.dataComboFood = this.orderSer.getTicketData().comboFood;
    this.dataMovie = this.orderSer.getTicketData().movie;
    this.dataTicket = this.orderSer.getTicketData().tickets;
  }

  groupByType(
    tickets: any[]
  ): { type: string; quantity: number; total: number }[] {
    const ticketMap = new Map<
      string,
      { type: string; quantity: number; total: number }
    >();
    for (const ticket of tickets) {
      if (!ticketMap.has(ticket.type)) {
        ticketMap.set(ticket.type, {
          type: ticket.type,
          quantity: 0,
          total: 0,
        });
      }
      const current = ticketMap.get(ticket.type)!;
      current.quantity += 1;
      current.total += ticket.price;
    }
    return Array.from(ticketMap.values());
  }

  get checkHaveSomeCombo() {
    return this.dataComboFood.some((e) => e.quantity > 0);
  }
}
