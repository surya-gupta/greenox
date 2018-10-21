import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user';
import { DataService } from '../data.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss']
})
export class InventoryComponent implements OnInit {

  user: User
  backgroundColor: string = "primary"
  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getLoginDetails().subscribe(
      data => {
        this.user = data;
      }
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

}
