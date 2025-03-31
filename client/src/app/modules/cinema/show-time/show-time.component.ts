import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie, MovieShowTime, Showtime } from 'src/app/models/project';
import { OrderService } from 'src/app/services/order.service';
import { ShowtimeService } from 'src/app/services/showtime.service';
import { environment } from 'src/environments/environment';

interface DateInfo {
  day: string;
  date: number;
  dateKey: string;
  formattedDate: string;
}

@Component({
  selector: 'app-show-time',
  templateUrl: './show-time.component.html',
  styleUrls: ['./show-time.component.scss'],
})
export class ShowTimeComponent implements OnInit {
  apiUrl = environment.BASE_URL;
  selectedDate: string | null = null;
  dates: DateInfo[] = [];
  moviesWithShowtimes: MovieShowTime[] = [];
  isLoading: boolean = true;

  // Map lưu trữ phim theo ngày
  moviesByDate: { [key: string]: MovieShowTime[] } = {};

  constructor(
    private router: Router,
    private orderSer: OrderService,
    private showtimeService: ShowtimeService
  ) {
    this.generateDates();
  }

  ngOnInit(): void {
    this.loadMoviesWithShowtimes();
  }

  loadMoviesWithShowtimes(): void {
    this.isLoading = true;
    this.showtimeService.getMoviesWithShowtimesForNextWeek().subscribe({
      next: (response) => {
        this.moviesWithShowtimes = response.data;
        // Nhóm phim theo ngày chiếu
        this.groupMoviesByDate();
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Lỗi khi tải lịch chiếu:', error);
        this.isLoading = false;
      },
    });
  }

  groupMoviesByDate(): void {
    this.moviesByDate = {};
    this.moviesWithShowtimes.forEach((movie) => {
      // Lặp qua từng suất chiếu của phim

      movie.showTimes.forEach((showtime) => {
        const date = showtime.date!;
        // Kiểm tra xem ngày này đã có trong map chưa
        if (!this.moviesByDate[date]) {
          this.moviesByDate[date] = [];
        }

        // Kiểm tra xem phim đã được thêm vào ngày này chưa
        const existingMovie = this.moviesByDate[date].find(
          (m) => m.filmID === movie.filmID
        );

        if (existingMovie) {
          // Nếu phim đã tồn tại, thêm suất chiếu mới vào
          existingMovie.showTimes?.push(showtime);
        } else {
          // Nếu phim chưa tồn tại, tạo một bản sao của phim với suất chiếu mới
          const movieCopy: MovieShowTime = {
            filmID: movie.filmID,
            name: movie.name,
            image: movie.image,
            showTimes: [showtime],
          };
          this.moviesByDate[date].push(movieCopy);
        }
      });
    });
  }

  orderTicket(showtime: Showtime, movie: MovieShowTime): void {
    // Lưu thông tin đã chọn vào localStorage hoặc service
    localStorage.setItem(
      'movie',
      JSON.stringify({
        movieId: movie.filmID,
        title: movie.name,
        image: movie.image,
        showtimeId: showtime.id,
        date: showtime.date,
        time: showtime.startShow,
        screen: showtime.screen,
      })
    );
    this.orderSer.resetMovieOrder();
    this.orderSer.setShowtime({
      id: showtime.id,
      startShow: showtime.startShow,
      screen: showtime.screen,
      date: showtime.date,
    });

    this.router.navigate(['/ticket/selectTicket']);
  }

  generateDates(): void {
    // Tạo mảng 7 ngày
    const today = new Date();
    const nextSevenDays = Array(7)
      .fill(null)
      .map((_, i) => {
        const date = new Date();
        date.setDate(today.getDate() + i);

        const displayDay = date.toLocaleDateString('vi-VN', {
          weekday: 'short',
        });
        const displayDate = date.getDate();
        const dateKey = date.toISOString().split('T')[0];

        return {
          day: displayDay,
          date: displayDate,
          dateKey: dateKey,
          formattedDate: date.toLocaleDateString('vi-VN'),
        };
      });

    this.dates = nextSevenDays;
    this.selectedDate = nextSevenDays[0].dateKey;
  }

  handleDateSelect(dateKey: string): void {
    this.selectedDate = dateKey;
  }

  getMoviesForSelectedDate(): MovieShowTime[] {
    if (!this.selectedDate) return [];
    return this.moviesByDate[this.selectedDate] || [];
  }

  getSelectedDateInfo(): DateInfo | undefined {
    if (!this.selectedDate) return undefined;
    return this.dates.find((d) => d.dateKey === this.selectedDate);
  }
}
