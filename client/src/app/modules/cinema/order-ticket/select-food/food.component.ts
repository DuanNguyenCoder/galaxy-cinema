import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { comboFood } from 'src/app/models/project';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.scss'],
})
export class FoodComponent {
  constructor(private router: Router, public cartData: OrderService) {}

  nextStep() {
    this.cartData.stepBreadcrumb += 1;
    this.router.navigate(['/ticket/confirm']);
  }

  action(item: comboFood, action: 'dec' | 'inc') {
    if (action == 'inc') {
      const comboFoodFind = this.cartData
        .getTicketData()
        .comboFood.find((e: comboFood) => e.name == item.name);
      comboFoodFind.quantity += 1;
    } else {
      const comboFoodFind = this.cartData
        .getTicketData()
        .comboFood.find((e: comboFood) => e.name == item.name);
      comboFoodFind.quantity -= 1;
    }
    this.cartData.reCalucate();
  }
}
