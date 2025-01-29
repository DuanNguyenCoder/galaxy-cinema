import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Showtime } from '../models/project';

@Injectable({
  providedIn: 'root',
})
export class ShowtimeService {
  constructor(private http: HttpClient) {}

  getMoviesWithShowtimesForNextWeek() {
    return this.http.get<any>(`${environment.BASE_URL}showtime/next-week`);
  }

  getAllShowTimebyFilm(filmId: number) {
    return this.http.get<any>(
      environment.BASE_URL + 'showtime/film/getAll/' + filmId
    );
  }

  getShowtimesByFilm(filmId: number) {
    return this.http.get<any>(`${environment.BASE_URL}showtime/film/${filmId}`);
  }

  createMovieShowTime(MovieShowTime: any): Observable<Showtime> {
    return this.http.post<Showtime>(
      environment.BASE_URL + 'showtime',
      MovieShowTime
    );
  }

  deleteShowtime(id: number): Observable<any> {
    return this.http.delete(`${environment.BASE_URL}showtime/${id}`);
  }
}
