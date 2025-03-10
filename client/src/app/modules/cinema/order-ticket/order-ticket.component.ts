import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SeatService } from 'src/app/services/seat.service';

@Component({
  selector: 'app-order-ticket',
  templateUrl: './order-ticket.component.html',
  styleUrls: ['./order-ticket.component.scss'],
})
export class OrderTicketComponent implements OnDestroy {
  constructor(private router: Router, private seatSer: SeatService) {}
  ngOnDestroy(): void {
    console.warn('-----------------chay destroy---------------');
    this.seatSer.turnOffAllSeat();
  }

  nextStep() {
    this.router.navigate(['/ticket/selectSeat']);
  }
}
