import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Customer {
  id: number;
  name: string;
  email: string;
  phone: string;
  status: 'active' | 'inactive';
}

export interface CustomerResponse {
  content: Customer[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiUrl = `${environment.BASE_URL}customer`;

  constructor(private http: HttpClient) {}

  // Lấy danh sách khách hàng có phân trang và tìm kiếm
  getCustomers(
    page: number = 0,
    size: number = 10,
    search: string = ''
  ): Observable<CustomerResponse> {
    const params = {
      page: page.toString(),
      size: size.toString(),
      search,
    };
    return this.http.get<CustomerResponse>(this.apiUrl, { params });
  }

  updateCustomer(
    id: number,
    customer: Partial<Customer>
  ): Observable<Customer> {
    return this.http.put<Customer>(`${this.apiUrl}/${id}`, customer);
  }

  // Xóa khách hàng
  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
