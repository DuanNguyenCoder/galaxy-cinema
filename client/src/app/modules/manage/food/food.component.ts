import { Component, OnInit } from '@angular/core';
import { Food } from 'src/app/models/project';
import { FoodService } from 'src/app/services/food.service';

@Component({
  selector: 'app-food-manage',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.scss'],
})
export class FoodComponent implements OnInit {
  ngOnInit(): void {}

  selectedFile: File | null = null;
  imageUrl: string | null = null;
  food: Food = {
    name: '',
    price: undefined,
    stock: undefined,
    type: 'snack',
  };

  constructor(private foodSer: FoodService) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(event: Event) {
    event.preventDefault();
    if (!this.selectedFile) {
      this.foodSer.createFood(this.food!);
    } else {
      const formData = new FormData();
      formData.append('file', this.selectedFile!);
      this.foodSer.createFood(this.food!, formData);
    }
  }
}
