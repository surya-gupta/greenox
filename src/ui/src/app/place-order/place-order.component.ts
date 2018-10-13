import { Component, OnInit } from '@angular/core';
import {OrderInfo} from './OrderInfo'
import {ORDERS} from './mock-orderInfo'
@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.scss']
})

export class PlaceOrderComponent implements OnInit {
  orderInfo:OrderInfo={id:1,name:'suyash'};
  orders=ORDERS;
  selectedOrder:OrderInfo;
onSelect(o:OrderInfo):void{
  this.selectedOrder=o;
}

  constructor() { }

  ngOnInit() {
  }

}
