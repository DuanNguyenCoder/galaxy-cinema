import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { environment } from 'src/environments/environment';
import { MatDialog } from '@angular/material/dialog';
import { Customer, Movie } from 'src/app/models/project';
import { ClientService } from 'src/app/services/client.service';

import { HelperService } from 'src/app/services/helper.service';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  imageUrl = environment.BASE_URL;
  dataClient!: Customer;
  dataMovie: Movie[] = [];
  HideSubNav: boolean = true;
  constructor(
    private dialog: HelperService,
    public clientSer: ClientService,
    private route: Router,
    public dialogMat: MatDialog,
    private filmSer: FilmService
  ) {
    this.clientSer.dataClient.subscribe((res) => {
      this.dataClient = res;
    });
    this.filmSer.filmDataActive.subscribe((res) => (this.dataMovie = res));
  }
  ngOnInit(): void {}

  login() {
    this.dialog.openDialogLogin();
  }
  review(movie: Movie) {
    const formattedName = movie.name!.replace(/\s+/g, '-').toLowerCase();
    this.route.navigate(['/review', formattedName]);
    localStorage.setItem('movie', JSON.stringify(movie));
    this.HideSubNav = true;
  }

  navReview() {
    this.HideSubNav = !this.HideSubNav;
  }

  navigate(path: string) {
    this.route.navigate([path]);
  }

  logout() {
    this.clientSer.dataClient.next({ isLogin: false });
    localStorage.removeItem('user');
    localStorage.removeItem('accessToken');
    this.dialog.openDialogLogin();
  }
  showAlgro() {
    // if (this.clientSer.dataClient.getValue().isLogin) {
    //   const dialogRef = this.dialogMat.open(DragableComponent);
    // } else {
    //   this.toastSer.showError('Thông báo', 'Chưa đăng nhập');
    // }
  }
}
