import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, switchMap } from 'rxjs';
import { Movie } from '../models/project';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ToastService } from './toast.service';

@Injectable({
  providedIn: 'root',
})
export class FilmService {
  public filmDataActive = new BehaviorSubject<Movie[]>([]);
  public filmData = new BehaviorSubject<Movie[]>([]);
  constructor(private http: HttpClient, private toastSer: ToastService) {
    this.getFilmActive();
  }

  getFilmActive() {
    this.http.get(environment.BASE_URL + 'film/active').subscribe((e: any) => {
      this.filmDataActive.next(e.data as Movie[]);
    });
  }
  getAllFilm(page: number, size: number) {
    const params = {
      page: page.toString(),
      size: size.toString(),
    };
    this.http
      .get(environment.BASE_URL + 'film', { params })
      .subscribe((e: any) => {
        this.filmData.next(e.data.content as Movie[]);
      });
  }

  deleteFilm(id: number) {
    return this.http.delete(environment.BASE_URL + 'film/' + id);
  }

  uploadImage(form: FormData): Observable<any> {
    return this.http.post(environment.BASE_URL + 'film/upload', form);
  }

  createFilm(film: Movie, form?: FormData): Observable<any> {
    if (!form) {
      return this.http.post(environment.BASE_URL + 'film', film);
    }
    return this.uploadImage(form).pipe(
      switchMap((e: any) => {
        film.image = e.data;
        return this.http.post(environment.BASE_URL + 'film', film);
      })
    );
  }
}
