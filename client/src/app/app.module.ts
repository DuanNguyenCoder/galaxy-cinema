import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SlickCarouselModule } from 'ngx-slick-carousel';
// import { AuthInterceptorService } from '../../src/app/core/interceptors/auth-interceptor.service';

///// Mat-angular

import { MatSliderModule } from '@angular/material/slider';
import { BrowserModule } from '@angular/platform-browser';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';

///// helper
import { NgToastModule } from 'ng-angular-popup';
import { default as Swiper } from 'swiper';
import { Navigation, Pagination } from 'swiper';

Swiper.use([Navigation, Pagination]);

///// component
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './modules/auth/login/login.component';
import { NotFoundComponent } from './modules/404/not-found.component';
import { MainComponent } from './modules/cinema/main/main.component';
import { OrderTicketComponent } from './modules/cinema/order-ticket/order-ticket.component';
import { TicketComponent } from './modules/cinema/order-ticket/select-ticket/ticket.component';
import { SeatComponent } from './modules/cinema/order-ticket/select-seat/seat.component';
import { BillComponent } from './modules/cinema/order-ticket/bill/bill.component';
import { ShowTimeComponent } from './modules/cinema/show-time/show-time.component';
import { TicketItemComponent } from './modules/cinema/main/ticket-item/ticket-item.component';

import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { EventComponent } from './layout/event/event.component';
import { ReviewComponent } from './modules/cinema/review/review.component';
import { FoodComponent } from './modules/cinema/order-ticket/select-food/food.component';
import { FoodComponent as foodManage } from './modules/manage/food/food.component';
import { ConfirmComponent } from './modules/cinema/order-ticket/confirm/confirm.component';
import { OrderSuccessComponent } from './modules/cinema/order-ticket/order-success/order-success.component';
import { TicketShowtimeComponent } from './modules/cinema/main/ticket-showtime/ticket-showtime.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { AppComponent } from './app.component';
import { BreadcrumbComponent } from './modules/cinema/order-ticket/breadcrumb/breadcrumb.component';
import { ManageComponent } from './modules/manage/manage.component';
import { FilmComponent } from './modules/manage/film/film.component';
import { ShowtimeComponent as showtimeManage } from './modules/manage/showtime/showtime.component';
import { CinemaComponent } from './modules/cinema/cinema.component';
import { CustomerComponent } from './modules/manage/customer/customer.component';
import { ReportComponent } from './modules/manage/report/report.component';
import { EditCustomerDialogComponent } from './modules/manage/customer/edit-customer-dialog/edit-customer-dialog.component';

@NgModule({
  declarations: [
    CinemaComponent,
    AppComponent,
    LoginComponent,
    NotFoundComponent,
    MainComponent,
    BreadcrumbComponent,
    OrderTicketComponent,
    TicketComponent,
    SeatComponent,
    BillComponent,
    ShowTimeComponent,
    TicketItemComponent,
    HeaderComponent,
    FooterComponent,
    EventComponent,
    ReviewComponent,
    foodManage,
    EditCustomerDialogComponent,
    FoodComponent,
    ConfirmComponent,
    OrderSuccessComponent,
    TicketShowtimeComponent,
    ManageComponent,
    FilmComponent,
    showtimeManage,
    CustomerComponent,
    ReportComponent,
  ],
  imports: [
    DragDropModule,
    MatSliderModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    AppRoutingModule,
    MatProgressSpinnerModule,
    FormsModule,
    MatInputModule,
    MatToolbarModule,
    MatButtonToggleModule,
    MatIconModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    HttpClientModule,
    NgToastModule,
    MatSelectModule,
    MatDialogModule,
    MatSidenavModule,
    MatTabsModule,
    MatMenuModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatListModule,
    MatExpansionModule,
    SlickCarouselModule,
  ],
  providers: [
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: AuthInterceptorService,
    //   multi: true,
    // },
    { provide: LocationStrategy, useClass: HashLocationStrategy },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
