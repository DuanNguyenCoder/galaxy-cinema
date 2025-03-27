import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-success',
  templateUrl: './order-success.component.html',
  styleUrls: ['./order-success.component.scss'],
})
export class OrderSuccessComponent {
  orderSuccess = new BehaviorSubject<boolean | null>(null);
  constructor(
    private route: ActivatedRoute,
    private orderSer: OrderService,
    private router: Router
  ) {
    this.route.queryParams.subscribe((params) => {
      const sessionId = params['session_id'];
      this.orderSer.checkPayment(sessionId).subscribe((res: any) => {
        if (res.status === 200) {
          this.orderSuccess.next(true);
        } else {
          this.orderSuccess.next(false);
        }
      });
    });
  }
  reidirect() {
    this.router.navigate(['/']);
  }
}
