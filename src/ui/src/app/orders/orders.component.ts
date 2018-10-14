import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Order } from '../shared/order';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: Order[]
  interval: any

  constructor(private data: DataService) { }

  ngOnInit() {
    this.refreshData()
    this.interval = setInterval(() => {
      this.refreshData();
    }, 5000);
  }

  refreshData() {
    this.data.getOrders().subscribe(
      data => this.orders = data
    );
  }
  orderCancelled(index, orderId) {
    this.data.orderCancelled(orderId).subscribe(
      data => this.orders.splice(index, 1)
    );
  }
  orderCompleted(index, orderId) {
    this.data.orderCompleted(orderId).subscribe(
      data => this.orders.splice(index, 1)
    );
  }

  timeDifference(time): string {
    var diff = Math.abs(new Date().getTime() - new Date(time).getTime()) / 1000
    var sec_num = parseInt(diff + "", 10); // don't forget the second param
    var hours = Math.floor(sec_num / 3600);
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
    var seconds = sec_num - (hours * 3600) - (minutes * 60);
    return hours + 'h' + minutes + 'm' + seconds + 's';
  }
}
