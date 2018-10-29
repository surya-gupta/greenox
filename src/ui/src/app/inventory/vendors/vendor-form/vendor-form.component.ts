import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { DataService } from 'src/app/data.service';
import { Vendor } from 'src/app/shared/vendor';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-vendor-form',
  templateUrl: './vendor-form.component.html',
  styleUrls: ['./vendor-form.component.scss']
})
export class VendorFormComponent implements OnInit {


  vendorForm: FormGroup
  submited = 'true';

  types: any = ['Service', 'Shop', 'Distributor']

  constructor(private fb: FormBuilder,
    private data: DataService,
    public dialogRef: MatDialogRef<VendorFormComponent>,
    @Inject(MAT_DIALOG_DATA) public _vendor: Vendor) {
  }

  ngOnInit() {
    this.renderVendorForm()
  }

  renderVendorForm() {
    this.vendorForm = this.fb.group({
      id: [null],
      name: [null, [Validators.required]],
      phoneNumber: [null, [Validators.required, Validators.minLength(10)]],
      description: [null, [Validators.required]],
      emailId: [null, [Validators.email]],
      type: [null, [Validators.required]]
    })
    this.vendorForm.patchValue(this._vendor)
  }

  submitHandler() {
    const formValue = this.vendorForm.value
    if (this.vendorForm.touched) {
      this.data.updateVendor(formValue).subscribe(
        data => {
          console.log('Vendor Saved!!!')
        }
      )
    }

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
