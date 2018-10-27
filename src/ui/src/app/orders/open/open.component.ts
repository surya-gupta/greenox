import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';
import { Order } from 'src/app/shared/order';
import { DataService } from 'src/app/data.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { DailogComponent } from 'src/app/shared/dailog/dailog.component';
import { containsElement } from '@angular/animations/browser/src/render/shared';
@Component({
  selector: 'app-order-open',
  templateUrl: './open.component.html',
  styleUrls: ['./open.component.scss']
})
export class OrderOpenComponent implements OnInit {
  orders: Order[]
  @Output() noOfOrderOpen = new EventEmitter<number>()
  interval: any
  subscription: Subscription
  summary: Map<string, number>
  objectKeys = Object.keys;
  constructor(private data: DataService, public dialog: MatDialog, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.refreshData()
    this.interval = setInterval(() => {
      this.refreshData()
    }, 10000)
  }
  refreshData() {
    this.subscription = this.data.getOrders().subscribe(
      data => {
        this.orders = data.orders
        this.summary = data.summary
        this.noOfOrderOpen.emit(data.orders.length)
      }
    )
  }
  orderCancelled(index, orderId) {
    const dialogRef = this.dialog.open(DailogComponent, {
      width: '250px',
      data: { task: 'Order', action: 'Cancel', reason: '' }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log("closing cancellation dailog ....")
      if (result != undefined) {
        this.data.orderCancelled(orderId, result).subscribe(
          data => {
            this.snackBar.open(`Order ${this.orderNumberForId(orderId)} is Cancelled!!!`, 'OK', {
              duration: 2000,
            })
            this.orders.splice(index, 1)
          })
      }
      console.log("closed cancellation dailog ....")
    })
  }

  orderCompleted(index, orderId) {
    this.data.orderCompleted(orderId, 'Delivered').subscribe(
      data => {
        this.snackBar.open(`Order ${this.orderNumberForId(orderId)} is Completed!!!`, 'OK', {
          duration: 2000,
        })
        this.orders.splice(index, 1)
      }
    );
  }

  orderNumberForId(orderId): number {
    var result = this.orders.find(order => order.id == orderId)
    return result.orderNumber
  }
  timeDifference(time): string {
    var diff = Math.abs(new Date().getTime() - new Date(time).getTime()) / 1000
    var sec_num = parseInt(diff + "", 10) // don't forget the second param
    var hours = Math.floor(sec_num / 3600)
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60)
    var seconds = sec_num - (hours * 3600) - (minutes * 60)
    return (hours < 10 ? '0' + hours : hours) + 'h' + (minutes < 10 ? '0' + minutes : minutes) + 'm' + (seconds < 10 ? '0' + seconds : seconds) + 's'
  }

  orderTimerNotification(time): string {
    var diff = Math.abs(new Date().getTime() - new Date(time).getTime()) / 1000
    var sec_num = parseInt(diff + "", 10) // don't forget the second param
    var hours = Math.floor(sec_num / 3600)
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60)
    if (hours > 0 || minutes > 15)
      return 'late'
    else {
      return 'on-schedule'
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}