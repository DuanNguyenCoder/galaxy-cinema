import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Breadcrumb } from 'src/app/models/project';
import { LoginComponent } from '../modules/auth/login/login.component';

@Injectable({
  providedIn: 'root',
})
export class HelperService {
  private breadcrumbs: Breadcrumb[] = [];
  dialogRef!: MatDialogRef<any>;
  public itemCarousel: any[] = [];

  constructor(private dialog: MatDialog) {
    this.addBreadcrumb('Chọn vé', '/ticket/selectTicket');
    this.addBreadcrumb('Chọn ghế', '/ticket/selectSeat');
    this.addBreadcrumb('Chọn đồ ăn', '/ticket/selectFood');
    this.addBreadcrumb('Xác nhận', '/ticket/confirm');
    this.addBreadcrumb('Đặt vé thành công', '/ticket/payment_infor');
  }

  getBreadcrumbs(): Breadcrumb[] {
    return this.breadcrumbs;
  }

  addBreadcrumb(label: string, url: string): void {
    const breadcrumb: Breadcrumb = { label, url };
    this.breadcrumbs.push(breadcrumb);
  }

  removeLastBreadcrumb(): void {
    this.breadcrumbs.pop();
  }

  resetBreadcrumbs(): void {
    this.breadcrumbs = [];
  }
  openDialogLogin() {
    this.dialogRef = this.dialog.open(LoginComponent);
  }

  closeDialogLogin() {
    if (this.dialogRef) {
      this.dialogRef.close();
    }
  }
}
