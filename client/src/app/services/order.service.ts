import { Injectable } from '@angular/core';
import { comboFood, Movie, Showtime, ticket } from '../models/project';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  public stepBreadcrumb: number = 1;
  private comboFood = [
    {
      name: 'Combo 1 small',
      price: 76000,
      quantity: 0,
      image: '../../../../assets/images/CB1-small.png',
    },
    {
      name: 'Combo 1 big',
      price: 79000,
      quantity: 0,
      image: '../../../../assets/images/CB1-big.png',
    },
    {
      name: 'Combo 2 big',
      price: 92000,
      quantity: 0,
      image: '../../../../assets/images/CB2-big.png',
    },
    {
      name: 'Combo family 1 snack',
      price: 129000,
      quantity: 0,
      image: '../../../../assets/images/CBF-snack.png',
    },
    {
      name: 'Combo family 2 snack',
      price: 179000,
      quantity: 0,
      image: '../../../../assets/images/CBF2-snack.png',
    },
    {
      name: 'Combo family 2 food',
      price: 179000,
      quantity: 0,
      image: '../../../../assets/images/CBF2-ff.png',
    },
  ];
  private ticketData: any = {
    showtime: null, // Showtime
    movie: JSON.parse(localStorage.getItem('movie')!) as Movie, // Sử dụng local storage tránh refresh mất dữ liệu
    tickets: [], // ticket
    comboFood: structuredClone(this.comboFood), // combofood
    totalPrice: 0, //number
    customer: null, // int
  };

  constructor(private http: HttpClient) {}

  setShowtime(showtime: Showtime) {
    this.ticketData.showtime = showtime;
  }
  resetMovieOrder() {
    this.ticketData = {
      showtime: null,
      movie: JSON.parse(localStorage.getItem('movie')!) as Movie,
      tickets: [],
      comboFood: structuredClone(this.comboFood),
      totalPrice: 0,
      customer: null,
    };
    this.stepBreadcrumb = 1;
  }
  setTickets(ticket: ticket) {
    this.ticketData.tickets.push(ticket);
    this.reCalucate();
  }

  reCalucate() {
    let totalComboPrice = this.ticketData.comboFood.reduce(
      (total: any, current: any) => {
        return current.quantity > 0
          ? total + current.price * current.quantity
          : total;
      },
      0
    );
    let totalTicketPrice = this.ticketData.tickets.reduce(
      (total: any, current: any) => total + current.price,

      0
    );

    this.ticketData.totalPrice = totalComboPrice + totalTicketPrice;
  }

  getTicketData() {
    return this.ticketData;
  }
  getTicketType() {
    const u22 = this.ticketData.tickets.filter((e: any) => e.type == 'U22');
    const adult = this.ticketData.tickets.filter((e: any) => e.type == 'ADULT');

    return { u22, adult };
  }

  processCardPayment() {
    // filter comboFood quantity > 0
    this.ticketData.comboFood = this.ticketData.comboFood.filter(
      (e: any) => e.quantity > 0
    );
    return this.http.post<any>(
      `${environment.BASE_URL}order/create_payment`,
      this.ticketData
    );
  }
  checkPayment(sessionId: string) {
    return this.http.get<any>(
      `${environment.BASE_URL}order/check-payment?session_id=${sessionId}`
    );
  }
}
