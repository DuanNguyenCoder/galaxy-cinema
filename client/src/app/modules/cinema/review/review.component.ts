import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movie, Review } from 'src/app/models/project';

import { ClientService } from 'src/app/services/client.service';
import { ReviewService } from 'src/app/services/review.service';
import { ToastService } from 'src/app/services/toast.service';

import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss'],
})
export class ReviewComponent implements OnInit {
  hideTrailer = true;
  imageUrl = environment.BASE_URL;

  public review = {
    filmID: 0,
    comment: 'comment',
    rate: 1,
    rateDate: new Date().toISOString().slice(0, 10),
  };
  constructor(
    private clientSer: ClientService,
    private toastSer: ToastService,
    public reviewSer: ReviewService,
    private route: ActivatedRoute
  ) {}
  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      const movie: Movie = JSON.parse(localStorage.getItem('movie')!);
      this.reviewSer.film = movie;
      this.review.filmID = this.reviewSer.film.id!;
      this.reviewSer.getUserReviewFilm(movie.id!);
    });
  }
  sendReview() {
    this.clientSer.dataClient.subscribe((e) => {
      e.isLogin
        ? this.reviewSer.postReview(this.review).subscribe((res) => {
            res.status === 200 &&
              this.toastSer.showSuccess('Thông Báo', 'Đánh giá thành công!');
            this.reviewSer.getUserReviewFilm(this.reviewSer.film.id!);
            this.review.comment = '';
            this.review.rate = 1;
          })
        : this.toastSer.showError('Login', 'Please login');
    });
  }
  trailer() {
    this.hideTrailer = !this.hideTrailer;
  }
  rating(rating: number) {
    this.review.rate = rating;
    console.warn(this.review);
  }

  showIcon(rating: number) {
    if (this.review.rate! > rating) {
      return 'star';
    }
    return 'star_border';
  }
}
