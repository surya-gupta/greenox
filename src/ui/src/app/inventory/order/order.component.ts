import { Component, OnInit, Input } from '@angular/core';
import { CategorizedInventory } from 'src/app/shared/categorizedInventory';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { DataService } from 'src/app/data.service';
import { Vendor } from 'src/app/shared/vendor';

@Component({
  selector: 'app-inventory-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class InventoryOrderComponent implements OnInit {

  categorizedInventory: CategorizedInventory[]
  vendors: Vendor[]
  invNum: number
  inventory: FormGroup
  loading = false
  success = false

  constructor(private fb: FormBuilder, private data: DataService) { }

  loadInventory() {
    this.data.getCategorisedInventory().subscribe(
      data => {
        this.categorizedInventory = data.inventory
        this.createCategorizedForm()
      }
    )
  }

  loadVendors() {
    this.data.getAllVendor().subscribe(
      data => {
        //console.log("getting all vendors --> " + data.length)
        this.vendors = data
      }
    )
  }

  ngOnInit() {

    this.createBaseForm()
    this.loadVendors()
    this.loadInventory()

  }

  createBaseForm() {
    this.inventory = this.fb.group({
      vendor: this.fb.group({
        id: [null],
        name: [null],
        phoneNumber: [null],
        description: [null],
        emailId: [null],
        type: [null]
      }),
      invNum: [null],
      note: [null],
      advanceAmount: [null],
      categorisedItems: this.fb.array([])
    })
  }

  createCategorizedForm() {
    if (this.categorizedInventory) {
      //console.log("getting all inventory  --> " + this.categorizedInventory.length)
      this.categorizedInventory.forEach(data => {
        //console.log(`Create form for ${JSON.stringify(data)}`)
        this.addCategorizedItems(data)
      })
    }
  }

  getVendor() {
    this.inventory.get('vendor')
  }

  get categorisedItemsForm() {
    return this.inventory.get('categorisedItems') as FormArray
  }

  addCategorizedItems(category: CategorizedInventory) {

    var categoryForm = this.fb.group({
      category: [category.category],
      inventoryItems: this.fb.array([])
    })
    var categorisedItemsForm = categoryForm.get('inventoryItems') as FormArray
    category.inventoryItems.forEach(data => {
      var itemForm = this.fb.group({
        item: [data],
        orderQuantity: [0],
        receivedQuantity: [0],
        costPerUnit: [0],
        netAmount: [0],
        note: [null]
      })
      categorisedItemsForm.push(itemForm)
    })
    this.categorisedItemsForm.push(categoryForm)
  }

  patchVendor(id) {
    console.log("Vendor selected: " + id)
    var foundVal = this.vendors.find(data => data.id == id)
    console.log("Vendor selected: " + JSON.stringify(foundVal))
    this.inventory.get('vendor').patchValue(foundVal)
  }

  async submitHandler() {
    this.loading = true;

    const formValue = this.inventory.value;

    try {
      await this.data.orderInventory(formValue).subscribe(
        data => {
          console.log(data)
          this.invNum= data.invNum
        }
      )
      this.success = true;
    } catch (err) {
      console.error(err)
    }
    this.loading = false;
  }
}
