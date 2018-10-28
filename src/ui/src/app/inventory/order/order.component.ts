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
  paymentModes: any = ["Cash", "Paytm", "PhonePe", "GPay", "Mobikwik", "FreeCharge"]
  invNum: number
  inventory: FormGroup
  loading = false
  success = false

  categoryBackGround = "accent"

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
        this.vendors = data
      }
    )
  }
  loadAndSetForm() {
    this.createBaseForm()
    this.loadVendors()
    this.loadInventory()
  }
  ngOnInit() {
    this.loadAndSetForm()
  }

  createBaseForm() {
    this.inventory = this.fb.group({
      vendor: this.fb.group({
        id: [null],
        name:new FormControl({ value: null, disabled: true }),
        phoneNumber: new FormControl({ value: null, disabled: true }),
        description: new FormControl({ value: null, disabled: true }),
        emailId: new FormControl({ value: null, disabled: true }),
        type: new FormControl({ value: null, disabled: true })
      }),
      invNum: [null],
      note: [null],
      advanceAmount: [null],
      paymentMode: new FormControl({ value: null, disabled: true }),
      categorisedItems: this.fb.array([])
    })
  }

  createCategorizedForm() {
    if (this.categorizedInventory) {
      this.categorizedInventory.forEach(data => {
        this.addCategorizedItems(data)
      })
    }
  }

  get vendor() {
    return this.inventory.get('vendor')
  }

  get categorisedItemsForm() {
    return this.inventory.get('categorisedItems') as FormArray
  }

  addCategorizedItems(category: CategorizedInventory) {

    var categoryForm = this.fb.group({
      category: [category.category],
      inventoryItems: this.fb.array([])
    })
    var itemsForm = categoryForm.get('inventoryItems') as FormArray
    category.inventoryItems.forEach(data => {
      var itemForm = this.fb.group({
        item: [data],
        orderQuantity: [0],
        receivedQuantity: [0],
        costPerUnit: [0],
        netAmount: [0],
        note: [null]
      })
      itemsForm.push(itemForm)
    })
    this.categorisedItemsForm.push(categoryForm)
  }

  patchVendor(id) {
    var foundVal = this.vendors.find(data => data.id == id)
    this.inventory.get('vendor').patchValue(foundVal)
  }

  async submitHandler() {
    this.loading = true;

    const formValue = this.inventory.value

    try {
      await this.data.orderInventory(formValue).subscribe(
        data => {
          this.invNum = data.invNum
        }
      )
      this.success = true
    } catch (err) {
      console.error(err)
    }
    this.loading = false
  }

  formReset() {
    this.inventory.reset()
    this.loadAndSetForm()
    this.success = false
  }

  addMoreItemToCategory(i) {

    var categoryForm = this.categorisedItemsForm.at(i)
    var itemsForm = categoryForm.get('inventoryItems') as FormArray
    var itemForm = this.fb.group({
      item: [null],
      orderQuantity: [0],
      receivedQuantity: [0],
      costPerUnit: [0],
      netAmount: [0],
      note: [null]
    })
    itemsForm.push(itemForm)
  }

  enablePaymentMode() {
    var advanceAmount = this.inventory.get('advanceAmount').value
    if (advanceAmount == undefined || advanceAmount == 0) {
      this.inventory.get("paymentMode").setValue(null)
      this.inventory.get("paymentMode").disable()
    } else {
      this.inventory.get("paymentMode").enable()
    }
  }
}
