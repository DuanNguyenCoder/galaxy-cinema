import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Customer } from 'src/app/models/project';

@Component({
  selector: 'app-edit-customer-dialog',
  templateUrl: './edit-customer-dialog.component.html',
  styleUrls: ['./edit-customer-dialog.scss'],
})
export class EditCustomerDialogComponent {
  customerForm: FormGroup;
  isEdit: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<EditCustomerDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { customer?: Customer }
  ) {
    this.isEdit = !!data.customer;
    this.customerForm = this.fb.group({
      name: [data.customer?.name || '', [Validators.required]],
      email: [
        data.customer?.email || '',
        [Validators.required, Validators.email],
      ],
      phone: [
        data.customer?.phone || '',
        [Validators.required, Validators.pattern(/^[0-9]{10}$/)],
      ],
      passwords: [
        data.customer?.passwords || '',
        [Validators.required, Validators.pattern(/^.{6,}$/)],
      ],
    });
  }

  onSubmit(): void {
    if (this.customerForm.valid) {
      const customerData = this.customerForm.value;
      this.dialogRef.close(customerData);
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
