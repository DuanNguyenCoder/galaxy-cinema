import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Breadcrumb } from 'src/app/models/project';
import { HelperService } from 'src/app/services/helper.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.scss'],
})
export class BreadcrumbComponent {
  public dataBread: Breadcrumb[];

  constructor(
    private router: Router,
    private helperSer: HelperService,
    public orderSer: OrderService
  ) {
    this.dataBread = this.helperSer.getBreadcrumbs();
  }

  stepBread(itemIndex: number, url: string) {
    if (url == '/ticket/selectTicket') {
      this.orderSer.stepBreadcrumb = itemIndex + 1;
      this.router.navigate([url]);
    }
    if (url == '/ticket/selectSeat') {
      this.orderSer.stepBreadcrumb = itemIndex + 1;
      this.router.navigate([url]);
    }
    if (url == '/ticket/selectFood') {
      this.orderSer.stepBreadcrumb = itemIndex + 1;
      this.router.navigate([url]);
    }
    if (url == '/ticket/confirm') {
      this.orderSer.stepBreadcrumb = itemIndex + 1;
      this.router.navigate([url]);
    }
  }
}
