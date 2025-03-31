import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../services/customer.service';
import { MatDialog } from '@angular/material/dialog';
import { EditCustomerDialogComponent } from './edit-customer-dialog/edit-customer-dialog.component';
import { ToastService } from 'src/app/services/toast.service';
import { PageEvent } from '@angular/material/paginator';
import { Customer } from 'src/app/models/project';

@Component({
  selector: 'app-customer-manage',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss'],
})
export class CustomerComponent implements OnInit {
  searchTerm: string = '';
  customers: Customer[] = [];
  currentPage = 0;
  pageSize = 10;
  totalElements = 0;
  totalPages = 0;
  loading = false;

  constructor(
    private customerService: CustomerService,
    private dialog: MatDialog,
    private toastSer: ToastService
  ) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.loading = true;
    this.customerService
      .getCustomers(this.currentPage, this.pageSize, this.searchTerm)
      .subscribe({
        next: (response: any) => {
          this.customers = response.data.content;
          this.totalElements = response.data.totalElements;
          this.totalPages = response.data.totalPages;
          this.loading = false;
        },
        error: (error) => {
          console.error('Error loading customers:', error);
          this.toastSer.showError('Lỗi khi tải danh sách khách hàng');
        },
      });
  }

  onSearch(): void {
    this.currentPage = 0;
    this.loadCustomers();
  }

  onPageChange(event: PageEvent): void {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadCustomers();
  }

  openEditDialog(customer?: Customer): void {
    const dialogRef = this.dialog.open(EditCustomerDialogComponent, {
      width: '500px',
      data: { customer },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (customer) {
          // Cập nhật khách hàng
          this.customerService.updateCustomer(customer.id!, result).subscribe({
            next: (response: any) => {
              const index = this.customers.findIndex(
                (c) => c.id === customer.id
              );
              if (index !== -1) {
                this.customers[index] = response.data;
              }
              this.toastSer.showSuccess('Cập nhật thông tin thành công');
            },
            error: (error) => {
              console.error('Error updating customer:', error);
              this.toastSer.showError('Lỗi khi cập nhật thông tin');
            },
          });
        }
      }
    });
  }

  deleteCustomer(customer: Customer): void {
    if (confirm(`Bạn có chắc chắn muốn xóa khách hàng ${customer.name}?`)) {
      this.customerService.deleteCustomer(customer.id!).subscribe({
        next: () => {
          this.customers = this.customers.filter((c) => c.id !== customer.id);
          this.toastSer.showSuccess('Đã xóa khách hàng thành công');
        },
        error: (error) => {
          console.error('Error deleting customer:', error);
          this.toastSer.showError('Lỗi khi xóa khách hàng');
        },
      });
    }
  }
}
