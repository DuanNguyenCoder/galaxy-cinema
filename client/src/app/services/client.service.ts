import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ToastService } from './toast.service';
import { BehaviorSubject } from 'rxjs';
import { RecommendService } from './recommend.service';
import { Customer } from '../models/project';
import { HelperService } from './helper.service';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  public dataClient = new BehaviorSubject<Customer>({ isLogin: undefined });

  constructor(
    private http: HttpClient,
    private toast: ToastService,
    private dialog: HelperService,
    private recommendSer: RecommendService
  ) {}

  getLogin(e: string, p: string) {
    this.http
      .post<any>(environment.BASE_URL + 'customer/login', {
        email: e,
        passwords: p,
      })
      .subscribe((res) => {
        if (res !== null) {
          localStorage.setItem('accessToken', res['token']);
          this.getProfile();

          this.toast.showSuccess(
            this.toast.title.SUCCESS,
            this.toast.content.LOGIN_SUCCESS
          );
          this.dialog.closeDialogLogin();
        } else {
          this.toast.showError(
            this.toast.title.LOGIN_FAIL,
            this.toast.content.LOGIN_FAIL
          );
        }
      });
  }

  getProfile() {
    const accessToken = localStorage.getItem('accessToken');
    const headers = accessToken
      ? new HttpHeaders().set('Authorization', `Bearer ${accessToken}`)
      : new HttpHeaders();
    if (accessToken) {
      this.http
        .get<any>(environment.BASE_URL + 'customer/profile', { headers })
        .subscribe((e) => {
          if (e.status === 200) {
            localStorage.setItem('user', JSON.stringify(e.data));
            const customer = {
              ...JSON.parse(localStorage.getItem('user')!),
              isLogin: true,
            };
            this.dataClient.next(customer);
            this.recommendSer.recommendForUserAndAlgorithm(customer.id);
          } else {
            this.dataClient.next({ isLogin: false });
          }
        });
    } else {
      this.dataClient.next({ isLogin: false });
    }
  }

  createCustomer(dataRegis: Customer) {
    return this.http.post<any>(
      environment.BASE_URL + 'customer/register',
      dataRegis
    );
  }
}
