import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Movie, Review } from '../models/project';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  public film!: Movie;
  public inforRating = {
    averageRating: 0,
    reviewListByFilm: [] as Review[],
  };

  constructor(private http: HttpClient) {}

  getUserReviewFilm(filmId: number) {
    this.http
      .get<any>(environment.BASE_URL + 'review/film/' + filmId)
      .subscribe((res) => {
        this.inforRating.averageRating = Math.round(res.averageRating);
        this.inforRating.reviewListByFilm = res.ratings;
      });
  }

  postReview(userReview: Review) {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
    });
    return this.http.post<any>(
      environment.BASE_URL + 'review/addNew',
      userReview,
      {
        headers,
      }
    );
  }

  updateReview(userReview: Review) {
    this.http.post<any>(environment.BASE_URL + 'review/update', userReview);
  }
}
