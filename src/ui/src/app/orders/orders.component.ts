import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { User } from '../shared/user';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  user: User
  noOfOrderOpen: number

  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getLoginDetails().subscribe(
      data => { this.user = data; }
    )
  }

  containsAuthority(role) {
    if (this.user && this.user.authorities && this.user.authorities.find(authority => authority.authority == role)) {
      return true
    }
    else {
      return false;
    }
  }

  updateNoOfOpenOrders(count) {
    this.noOfOrderOpen = count;
  }
}