import { Injectable } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  constructor(private toast: NgToastService) {}

  time: number = 2500;

  title = {
    LOGIN_FAIL: 'Lỗi đăng nhập',
    SUCCESS: 'Thành công',
    FAIL: 'Thất bại',
    INVALID: 'Không hợp lệ',
  };

  content = {
    LOGIN_FAIL: 'Sai mail hoặc mật khẩu!',
    REQUEST_LOGIN: 'Vui lòng đăng nhập!',
    LOGIN_SUCCESS: 'Đăng nhập thành công!',
    ADD_FAIL: 'Thêm không thành công!',
    ADD_SUCCESS: 'Thêm thành công!',
    DELETE_FAIL: 'Xóa không thành công!',
    DELETE_SUCCESS: 'Xóa thành công!',
    NAME_INVALID: 'Tên không hợp lệ!',
  };

  showInfo(title?: string, content?: string) {
    this.toast.info({
      detail: title,
      summary: content,
      duration: this.time,
    });
  }
  showSuccess(title?: string, content?: string) {
    this.toast.success({
      detail: title,
      summary: content,
      duration: this.time,
    });
  }
  showError(title?: string, content?: string) {
    this.toast.error({
      detail: title,
      summary: content,
      duration: this.time,
    });
  }
}
