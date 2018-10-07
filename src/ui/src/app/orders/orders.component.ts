import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders$: Object;
  
  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getOrders().subscribe(
      data => this.orders$ = data
    );
  }

}
