import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../../src/app/core/guards/auth.guard';
import { MainComponent } from './modules/cinema/main/main.component';
import { OrderTicketComponent } from './modules/cinema/order-ticket/order-ticket.component';
import { SeatComponent } from './modules/cinema/order-ticket/select-seat/seat.component';
import { TicketComponent } from './modules/cinema/order-ticket/select-ticket/ticket.component';
import { ShowTimeComponent } from './modules/cinema/show-time/show-time.component';
import { ReviewComponent } from './modules/cinema/review/review.component';
import { FoodComponent } from './modules/cinema/order-ticket/select-food/food.component';
import { ConfirmComponent } from './modules/cinema/order-ticket/confirm/confirm.component';
import { OrderSuccessComponent } from './modules/cinema/order-ticket/order-success/order-success.component';
import { TicketShowtimeComponent } from './modules/cinema/main/ticket-showtime/ticket-showtime.component';
import { ManageComponent } from './modules/manage/manage.component';
import { NotFoundComponent } from './modules/404/not-found.component';
import { CinemaComponent } from './modules/cinema/cinema.component';

const routes: Routes = [
  {
    path: '',
    component: CinemaComponent,
    children: [
      {
        path: '',
        component: MainComponent,
      },
      {
        path: 'movie/:name',
        component: TicketShowtimeComponent,
      },
      {
        path: 'review/:movieName',
        component: ReviewComponent,
      },
      {
        path: 'show-time',
        component: ShowTimeComponent,
      },
      {
        path: 'ticket',
        component: OrderTicketComponent,
        canActivate: [AuthGuard],
        children: [
          { path: 'selectTicket', component: TicketComponent },
          { path: 'selectSeat', component: SeatComponent },
          { path: 'selectFood', component: FoodComponent },
          { path: 'confirm', component: ConfirmComponent },
        ],
      },
      {
        path: 'payment_infor',
        component: OrderSuccessComponent,
        canActivate: [AuthGuard],
      },
    ],
  },
  {
    path: 'manage',
    component: ManageComponent,
    canActivate: [AuthGuard],
  },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
