import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Menu} from './ordermenu/Menu'

@Injectable({
  providedIn: 'root'
})
export class MenuService {
 
 
  constructor(private httpClient:HttpClient) { }

  getMenuJSON():Observable<Menu[]>{
   return this.httpClient.get<Menu[]>('src/app/menu.json');
  
  }
}
