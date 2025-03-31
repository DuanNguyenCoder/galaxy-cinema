import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ClientService } from 'src/app/services/client.service';
import { ToastService } from 'src/app/services/toast.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  isDisable: boolean = true;
  formData!: FormGroup;

  name: string = '';
  phone: string = '';
  mail: string = '';
  passwords: string = '';
  confirmPass: string = '';
  passwordMismatch: boolean = false;

  constructor(
    private fb: FormBuilder,
    private loginSer: ClientService,
    private clientSer: ClientService,
    private toast: ToastService,
    private router: Router,

    public dialogRef: MatDialogRef<LoginComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  ngOnInit(): void {
    this.formData = this.fb.group({
      userNameOrEmailAddress: ['', Validators.compose([Validators.required])],
      password: [
        '',
        Validators.compose([Validators.required, Validators.minLength(5)]),
      ],
      rememberClient: [false],
    });
  }

  validateEmpty() {
    const userName = (
      document.querySelector('input[type="email"]') as HTMLInputElement
    ).value;
    const userPassword = (
      document.querySelector('input[type="password"]') as HTMLInputElement
    ).value;
    userName !== '' && userPassword !== ''
      ? (this.isDisable = false)
      : (this.isDisable = true);
  }

  onSubmit() {
    const mail = this.formData.value.userNameOrEmailAddress;
    const pass = this.formData.value.password;

    this.loginSer.getLogin(mail, pass);
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  openForgetPass() {
    console.warn('forget');
  }

  register() {
    if (!this.passwordMismatch) {
      if (!this.validateEmail(this.mail)) {
        this.toast.showError('Thông báo', 'email không hợp lệ');
        return;
      } else if (this.name == '' || this.name.length < 3) {
        this.toast.showError('Thông báo', 'tên không hợp lệ');
        return;
      } else if (this.passwords == '' || this.passwords.length < 5) {
        this.toast.showError('Thông báo', 'password không hợp lệ');
        return;
      }

      this.clientSer
        .createCustomer({
          name: this.name,
          phone: this.phone,
          email: this.mail,
          passwords: this.passwords,
        })
        .subscribe((e: any) => {
          if (e.status === 200) {
            this.name = '';
            this.phone = '';
            this.mail = '';
            this.passwords = '';
            this.confirmPass = '';
            this.toast.showSuccess(
              this.toast.title.SUCCESS,
              'Tạo tài khoản thành công!!'
            );
          } else {
            this.toast.showError(this.toast.title.FAIL, e.message);
          }
        });
    }
  }
  checkPasswordMatch() {
    if (this.passwords !== this.confirmPass) {
      this.passwordMismatch = true;
    } else {
      this.passwordMismatch = false;
    }
  }
  validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
}
