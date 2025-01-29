import { Component, OnInit } from '@angular/core';
import { FilmService } from 'src/app/services/film.service';
import { Movie } from 'src/app/models/project';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
})
export class MainComponent implements OnInit {
  dataMovieActive: Movie[] = [];

  slideConfig = {
    slidesToShow: 5,
    slidesToScroll: 1,
    dots: false,
    infinite: true,
    autoplay: true,
    autoplaySpeed: 3000,
    arrows: true,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2,
        },
      },
      {
        breakpoint: 576,
        settings: {
          slidesToShow: 1,
        },
      },
    ],
  };

  constructor(private filmService: FilmService) {}

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.filmService.filmDataActive.subscribe((res) => {
      this.dataMovieActive = res;
    });
  }
}
