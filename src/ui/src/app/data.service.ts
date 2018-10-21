import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderAndSummary } from './shared/order-and-summary';
import { User } from './shared/user';
import { CategorizedInventory } from './shared/categorizedInventory';
import { Vendor } from './shared/vendor';
import { InventoryOrder } from './shared/inventory-order';
@Injectable({
  providedIn: 'root'
})
export class DataService {

  baseURL = environment.baseURL
  httpOption = environment.httpOptions
  constructor(private http: HttpClient) { }

  getLoginDetails() {
    return this.http.get<User>('api/user')
  }

  getOrders(): Observable<OrderAndSummary> {
    var url = `${this.baseURL}/order/open`
    return this.http.get<OrderAndSummary>(url)
  }

  getCategorisedInventory() {
    var url = `${this.baseURL}/inventory/category`
    return this.http.get<any>(url)
  }

  addNewVendor(vendor) {
    var url = `${this.baseURL}/inventory/vendor/create`
    return this.http.post(url, vendor)
  }

  orderInventory(inventory) {
    var url = `${this.baseURL}/inventory/create`
    return this.http.post<InventoryOrder>(url, inventory)
  }

  getAllVendor() {
    var url = `${this.baseURL}/inventory/vendor/all`
    return this.http.get<Vendor[]>(url)
  }

  orderCancelled(orderId) {
    var url = `${this.baseURL}/order/update/${orderId}/status/CANCELLED`
    return this.http.get(url)

  }

  orderCompleted(orderId) {
    var url = `${this.baseURL}/order/update/${orderId}/status/COMPLETED`
    return this.http.get(url)
  }

  getUsers() {
    return this.http.get('https://jsonplaceholder.typicode.com/users')
  }

  getUser(userId) {
    return this.http.get('https://jsonplaceholder.typicode.com/users/' + userId)
  }
  
}
