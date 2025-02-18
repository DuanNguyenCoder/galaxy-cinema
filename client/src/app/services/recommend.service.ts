import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { FilmService } from './film.service';

@Injectable({
  providedIn: 'root',
})
export class RecommendService {
  constructor(private http: HttpClient, private filmSer: FilmService) {}

  recommendForUserAndAlgorithm(idUser: number) {
    const movieActive = this.filmSer.filmDataActive.getValue();

    this.http
      .get<any>(environment.BASE_URL + 'recommend/a/' + idUser)
      .subscribe((res) => {
        // sap xep phim
        console.warn(res.dataFilm);
        const intersected = res.dataFilm.filter((obj1: any) => {
          const matchedObj = movieActive.find((obj2) => obj2.id === obj1.id);
          return matchedObj !== undefined;
        });

        const nonIntersected = movieActive.filter((obj2) => {
          const matchedObj = res.dataFilm.find(
            (obj1: any) => obj1.id === obj2.id
          );
          return matchedObj === undefined;
        });

        const result = intersected.concat(nonIntersected);
        this.filmSer.filmDataActive.next(result);
      });
  }
}
