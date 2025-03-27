import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ToastService } from './toast.service';
import { Observable } from 'rxjs';
import { Food } from 'src/app/models/project';

@Injectable({
  providedIn: 'root',
})
export class FoodService {
  constructor(private http: HttpClient, private toast: ToastService) {}

  uploadImage(form: FormData): Observable<Object> {
    return this.http.post(environment.BASE_URL + 'food/upload', form);
  }
  createFood(food: Food, form?: FormData) {
    if (form == undefined) {
      this.http
        .post(environment.BASE_URL + 'food', food)
        .subscribe((e: any) => {});
    } else {
      this.uploadImage(form).subscribe((e: any) => {
        food.image = e.data;
        this.http
          .post(environment.BASE_URL + 'food', food)
          .subscribe((e: any) => {});
      });
    }
  }
}
