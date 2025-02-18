import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TypeTicket } from 'src/app/models/enum';

import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss'],
})
export class TicketComponent implements OnInit {
  public adultQuantity: number = 0;
  public u22Quantity: number = 0;
  public test = 3;
  public adultTotal!: number;
  public u22Total!: number;
  public type!: TypeTicket;

  constructor(private router: Router, public orderSer: OrderService) {
    this.adultQuantity = this.orderSer.getTicketType().adult.length;
    this.u22Quantity = this.orderSer.getTicketType().u22.length;
  }

  ngOnInit(): void {}

  calculate(type: TypeTicket) {
    if (type === TypeTicket.ADULT) {
      this.adultTotal = 75000 * this.adultQuantity;
    } else if (type === TypeTicket.U22) {
      this.u22Total = 65000 * this.u22Quantity;
    }
  }

  action(type: 'U22' | 'ADULT', action: 'inc' | 'dec') {
    if (action == 'inc') {
      type == 'U22' ? (this.u22Quantity += 1) : (this.adultQuantity += 1);
      type == 'U22'
        ? this.orderSer.setTickets({ price: 65000, type })
        : this.orderSer.setTickets({ price: 75000, type });
    } else {
      type == 'U22' ? (this.u22Quantity -= 1) : (this.adultQuantity -= 1);
      const index = this.orderSer
        .getTicketData()
        .tickets.findLastIndex((e: any) => e.type == type);
      this.orderSer.getTicketData().tickets.splice(index, 1);
    }
    this.orderSer.reCalucate();
  }

  nextStep() {
    this.orderSer.stepBreadcrumb += 1;
    this.router.navigate(['/ticket/selectSeat']);
  }
}
