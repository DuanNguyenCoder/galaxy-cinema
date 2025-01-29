import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ShowtimeService } from 'src/app/services/showtime.service';
import { Movie, MovieShowTime, Showtime } from 'src/app/models/project';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DateService } from 'src/app/services/date.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-showtime-manage',
  templateUrl: './showtime.component.html',
  styleUrls: ['./showtime.component.scss'],
})
export class ShowtimeComponent {
  MovieShowTimes: Showtime[] = [];
  showtimeReq = {
    startShow: this.dateSer.getCurrentDate(),
    screen: null,
    filmID: this.data.film.id,
  };
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { film: Movie },
    public dateSer: DateService,
    private MovieShowTimeService: ShowtimeService,
    private toastSer: ToastService
  ) {
    this.loadMovieShowTimes();
  }

  loadMovieShowTimes(): void {
    this.MovieShowTimeService.getAllShowTimebyFilm(
      this.showtimeReq.filmID!
    ).subscribe((res) => {
      this.MovieShowTimes = res.data;
    });
  }

  onSubmit(): void {
    if (!this.validate()) {
      this.toastSer.showError('Error', 'invalid input');
    } else {
      this.MovieShowTimeService.createMovieShowTime(this.showtimeReq).subscribe(
        () => {
          this.toastSer.showSuccess('Success', 'Showtime created');
          this.loadMovieShowTimes();
          this.showtimeReq.startShow = '';
        }
      );
    }
  }
  validate(): boolean {
    if (this.showtimeReq.startShow === '' || this.showtimeReq.screen === null) {
      return false;
    }
    return true;
  }
  editMovieShowTime(): void {}

  deleteShowtime(id: number) {
    if (confirm('Confirm delete?')) {
      this.MovieShowTimeService.deleteShowtime(id).subscribe({
        next: (response) => {
          this.loadMovieShowTimes();
          this.toastSer.showSuccess('Success', 'Delete Success');
        },
        error: (error) => {
          this.toastSer.showError('Error', 'Showtime not found');
        },
      });
    }
  }
}
