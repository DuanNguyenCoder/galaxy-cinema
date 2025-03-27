import { Component, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Movie } from 'src/app/models/project';
import { FilmService } from 'src/app/services/film.service';
import { environment } from 'src/environments/environment';
import { ShowtimeComponent } from '../showtime/showtime.component';
import { ToastService } from 'src/app/services/toast.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-film-manage',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.scss'],
})
export class FilmComponent {
  readonly dialog = inject(MatDialog);
  apiUrl = environment.BASE_URL;
  currentPage = 0;
  pageSize = 10;
  imageSrc: string | null = null;
  mouseX: number = 0;
  mouseY: number = 0;
  genres = [
    'Hành động',
    'Tình cảm',
    'Khoa học viễn tưởng',
    'Tâm lý',
    'Hoạt hình',
    'Phiêu lưu',
  ];
  isEdit: boolean = false;
  selectedFile: File | null = null;
  imageUrl: string | null = null;
  filmList?: Movie[];
  film: Movie = {
    ageLimited: 16,
    image: null,
    time: '',
    name: '',
    typefilm: '',
    description: '',
    link: '',
    author: '',
    country: '',
    active: 0,
  };

  constructor(private filmSer: FilmService, public toastSer: ToastService) {
    this.filmSer.getAllFilm(this.currentPage, this.pageSize);
    this.filmSer.filmData.subscribe((e: any) => {
      this.filmList = e;
    });
  }

  changeMode() {
    if (!this.isEdit) {
      this.resetForm();
    }
    this.isEdit
      ? this.toastSer.showInfo('Thông báo', 'Bạn đang sử dụng chế độ chỉnh sửa')
      : this.toastSer.showSuccess(
          'Thông báo',
          'Bạn đang sử dụng chế độ lưu mới'
        );
  }

  editFilm(film: Movie) {
    this.isEdit = true;
    this.changeMode();
    this.film = {
      ...film,
    };
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(event: Event) {
    event.preventDefault();

    let formData: FormData | undefined;
    if (this.selectedFile) {
      formData = new FormData();
      formData.append('file', this.selectedFile);
    }

    this.filmSer.createFilm(this.film!, formData).subscribe({
      next: (e: any) => {
        const notice = this.isEdit ? 'Sửa' : 'Thêm mới';
        if (e.status === 201) {
          this.toastSer.showSuccess('Thông báo', `${notice} thành công!`);
        } else {
          this.toastSer.showError('Thông báo', `${notice} thất bại!`);
        }

        this.filmSer.getAllFilm(this.currentPage, this.pageSize);
      },
      error: (error) => {
        this.toastSer.showError('Thông báo', 'Có lỗi xảy ra khi thêm mới!');
      },
      complete: () => {
        this.isEdit = false;
        this.resetForm();
      },
    });
  }
  resetForm() {
    this.film = {
      ageLimited: 16,
      image: null,
      time: '',
      name: '',
      typefilm: '',
      description: '',
      link: '',
      author: '',
      country: '',
      active: 0,
    };
  }
  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.filmSer.getAllFilm(this.currentPage, this.pageSize);
  }
  deleteFilm(id: number) {
    if (confirm('Bạn có chắc chắn muốn xóa phim này không?')) {
      this.filmSer.deleteFilm(id).subscribe((e: any) => {
        e.status === 200
          ? this.toastSer.showSuccess('Thông báo', 'Xóa phim thành công')
          : this.toastSer.showError('Thông báo', 'Xóa phim thất bại');
        this.filmSer.getAllFilm(this.currentPage, this.pageSize);
      });
    }
  }

  showImage(event: MouseEvent, src: string) {
    this.imageSrc = src;
    this.mouseX = event.pageX + 10; // Cách con trỏ 10px
    this.mouseY = event.pageY + 10;
  }

  hideImage() {
    this.imageSrc = null;
  }

  openDialog(film: Movie) {
    this.dialog.open(ShowtimeComponent, {
      data: { film },
    });
  }
}
