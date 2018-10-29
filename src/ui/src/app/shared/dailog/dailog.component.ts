import { Component, OnInit } from '@angular/core';
import { DialogData } from '../dialog-data';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Inject } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-dailog',
  templateUrl: './dailog.component.html',
  styleUrls: ['./dailog.component.scss']
})


export class DailogComponent implements OnInit {
  dialog: FormGroup

  ngOnInit() {
    this.dialog = this.fb.group({ reason: [null] })
  }
  constructor(
    public dialogRef: MatDialogRef<DailogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private fb: FormBuilder) { }

  onNoClick(): void {
    this.data.reason = undefined
    this.dialogRef.close();
  }
  updateReason() {
    this.data.reason = this.dialog.get('reason').value
  }

}