import { Injectable } from '@angular/core'; 
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }
  getUser(userId) {
    return this.http.get('https://jsonplaceholder.typicode.com/users/'+userId)
  }

  getOrders() {
    return this.http.get('api/order/open')
  }

  getUsers() {
    return this.http.get('https://jsonplaceholder.typicode.com/users')
  }
}
