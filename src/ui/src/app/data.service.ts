import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { orderAndSummary } from './shared/orderAndSummary';
@Injectable({
  providedIn: 'root'
})
export class DataService {

  baseURL = environment.baseURL
  httpOption = environment.httpOptions
  constructor(private http: HttpClient) { }

  getUser(userId) {
    return this.http.get('https://jsonplaceholder.typicode.com/users/' + userId)
  }

  getOrders(): Observable<orderAndSummary> {
    var url = '${this.baseURL}/order/open'
    return this.http.get<orderAndSummary>(url, this.httpOption)
  }
 

  orderCancelled(orderId) {
    var url = `${this.baseURL}/order/update/${orderId}/status/CANCELLED`
    return this.http.get(url, this.httpOption)

  }
  
  orderCompleted(orderId) {
    var url = `${this.baseURL}/order/update/${orderId}/status/COMPLETED`
    return this.http.get(url, this.httpOption)
  }

  addOrder(order) {
    return this.http.get('api/order/open')
  }
 
  getUsers() {
    return this.http.get('https://jsonplaceholder.typicode.com/users')
  }
}
