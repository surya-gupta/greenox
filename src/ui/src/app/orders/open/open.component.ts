import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Order } from 'src/app/shared/order';
import { DataService } from 'src/app/data.service';
@Component({
  selector: 'app-order-open',
  templateUrl: './open.component.html',
  styleUrls: ['./open.component.scss']
})
export class OrderOpenComponent implements OnInit {
  orders: Order[]
  interval: any
  subscription: Subscription
  summary: Map<string, number>
  objectKeys = Object.keys;
  constructor(private data: DataService) { }

  ngOnInit() {
    this.refreshData()
    this.interval = setInterval(() => {
      this.refreshData()
    }, 10000)
  }
  refreshData() {
    this.subscription = this.data.getOrders().subscribe(
      data => { this.orders = data.orders; this.summary = data.summary }
    )
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
    var sec_num = parseInt(diff + "", 10) // don't forget the second param
    var hours = Math.floor(sec_num / 3600)
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60)
    var seconds = sec_num - (hours * 3600) - (minutes * 60)
    return (hours < 10 ? '0' + hours : hours) + 'h' + (minutes < 10 ? '0' + minutes : minutes) + 'm' + (seconds < 10 ? '0' + seconds : seconds) + 's'
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
