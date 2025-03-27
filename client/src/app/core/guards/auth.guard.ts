import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { Observable, filter, take, map } from 'rxjs';
import { ClientService } from 'src/app/services/client.service';
import { HelperService } from 'src/app/services/helper.service';
import { ToastService } from 'src/app/services/toast.service';
import { Customer } from 'src/app/models/project';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private clientSer: ClientService,
    private toastSer: ToastService,
    private helper: HelperService
  ) {}

  canActivate(): Observable<boolean> {
    return this.clientSer.dataClient.pipe(
      filter((customer: Customer) => customer.isLogin !== undefined),
      take(1),
      map((customer: Customer) => {
        if (customer.isLogin) {
          return true;
        }
        this.toastSer.showError(
          this.toastSer.title.FAIL,
          this.toastSer.content.REQUEST_LOGIN
        );
        this.helper.openDialogLogin();
        return false;
      })
    );
  }
}
