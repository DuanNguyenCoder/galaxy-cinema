import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Food } from 'src/app/models/project';
import { FoodService } from 'src/app/services/food.service';

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.scss'],
})
export class ManageComponent implements OnInit {
  ngOnInit(): void {}

  selectedFile: File | null = null;
  imageUrl: string | null = null;
  food?: Food;

  constructor(private http: HttpClient, private foodSer: FoodService) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(event: Event) {
    event.preventDefault();
    if (!this.selectedFile) {
      alert('Vui lòng chọn ảnh');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.foodSer.createFood(this.food!, formData);
  }
}
