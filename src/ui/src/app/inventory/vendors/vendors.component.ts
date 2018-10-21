import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { DataService } from 'src/app/data.service';
import { Item } from 'src/app/shared/item';

@Component({
  selector: 'app-inventory-vendors',
  templateUrl: './vendors.component.html',
  styleUrls: ['./vendors.component.scss']
})
export class InventoryVendorsComponent implements OnInit {

  vendor: FormGroup
  loading = false;
  success = false;


  cart: Item[]
  types: any = ['Service', 'Shop', 'Distribution']

  constructor(private fb: FormBuilder, private data: DataService) {

  }

  ngOnInit() {
    this.vendor = this.fb.group({
      name: [null, [Validators.required]],
      phoneNumber: [null, [Validators.required, Validators.minLength(10)]],
      description: [null, [Validators.required]],
      emailId: [null, [Validators.email]],
      type: [null, [Validators.required]]
    })
  }

  async submitHandler() {
    this.loading = true;

    const formValue = this.vendor.value;

    try {
      await this.data.addNewVendor(formValue).subscribe(
        data => {
          console.log(data)
        }
      )
      this.success = true;
    } catch (err) {
      console.error(err)
    }
    this.loading = false;
  }
}
