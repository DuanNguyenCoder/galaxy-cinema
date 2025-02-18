import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seat } from 'src/app/models/project';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SeatService {
  // Tạo mảng ghế

  public seats: Seat[] = [];
  public rows = ['A', 'B', 'C', 'D', 'E', 'F'];

  constructor(private http: HttpClient) {
    this.generateSeat();
  }

  generateSeat() {
    this.rows.forEach((row) => {
      for (let i = 1; i <= 14; i++) {
        const seat: Seat = {
          number: i,
          selected: false,
          row: row,
          ordered: false,
        };
        this.seats.push(seat);
      }
    });
  }

  turnOffAllSeat() {
    if (this.seats.length !== 0) {
      this.seats.forEach((seat) => {
        for (let i = 1; i <= 14; i++) {
          if (seat.selected || seat.ordered) {
            seat.selected = false;
            seat.ordered = false;
          }
        }
      });
    }
  }

  getSeatOrdered(showtimeID: number) {
    this.http
      .get<any>(environment.BASE_URL + 'showtime/seatOrdered/' + showtimeID)
      .subscribe((res) => {
        if (res.data.length !== 0) {
          for (const seat of this.seats) {
            // Tách số và hàng của ghế
            // Kiểm tra xem ghế có trong danh sách đã ordered hay không

            if (res.data.includes(`${seat.row}${seat.number}`)) {
              seat.ordered = true;
            }
          }
        }
      });
  }
}
