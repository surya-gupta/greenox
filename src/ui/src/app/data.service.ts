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

  getPosts() {
    return this.http.get('http://localhost:8080/api/order/open')
  }

  getUsers() {
    return this.http.get('https://jsonplaceholder.typicode.com/users')
  }
}
