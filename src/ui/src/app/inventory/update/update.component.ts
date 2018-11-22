import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { DataService } from 'src/app/data.service';
import { Vendor } from 'src/app/shared/vendor';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Inject } from '@angular/core';
import { InventoryOrder } from 'src/app/shared/inventory-order';
import { CategorizedInventory } from 'src/app/shared/categorizedInventory';


@Component({
  selector: 'app-inventory-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class InventoryUpdateComponent implements OnInit {

  inventory: FormGroup
  submited = 'true';
  paymentModes: any = ["Cash", "Paytm", "PhonePe", "GPay", "Mobikwik", "FreeCharge"]
  vendors: Vendor[]
  categorizedInventory: CategorizedInventory[]

  categoryBackGround = "accent"

  constructor(private fb: FormBuilder,
    private data: DataService,
    public dialogRef: MatDialogRef<InventoryUpdateComponent>,
    @Inject(MAT_DIALOG_DATA) public _order: InventoryOrder) {
  }

  submitHandler() {
    const formValue = this.inventory.value
    if (this.inventory.touched) {
      this.data.orderInventory(formValue).subscribe(
        data => {
          console.log('Order Saved!!!')
        }
      )
    }
  }

  loadAndSetForm() {
    this.createBaseForm()
    this.loadVendors()
    this.loadInventory()
  }

  ngOnInit() {
  }

  createBaseForm() {
    this.inventory = this.fb.group({
      vendor: this.fb.group({
        id: [null],
        name: new FormControl({ value: null, disabled: true }),
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
        this.vendors = data.filter(record=>record.status!='Discontinued')
      }
    )
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

  onNoClick(): void {
    this.dialogRef.close();
  }
}
