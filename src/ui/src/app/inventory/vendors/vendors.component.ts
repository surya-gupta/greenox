import { DataService } from 'src/app/data.service';
import { Vendor } from 'src/app/shared/vendor';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { MatDialog, MatSnackBar } from '@angular/material';
import { VendorFormComponent } from './vendor-form/vendor-form.component';
@Component({
  selector: 'app-inventory-vendors',
  templateUrl: './vendors.component.html',
  styleUrls: ['./vendors.component.scss']
})
export class InventoryVendorsComponent implements OnInit {

  currentVendor: Vendor
  vendors: Vendor[]
  dataSource = new MatTableDataSource(this.vendors);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  pageSizeOptions: number[] = [5, 10, 25, 50];
  displayedColumns: string[] = ['name', 'phoneNumber', 'emailId', 'status', 'type', 'description', 'id'];
  constructor(private data: DataService, public dialog: MatDialog, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.loadVendors()
  }

  loadVendors() {
    this.data.getAllVendor().subscribe(
      data => {
        this.vendors = data
        this.dataSource = new MatTableDataSource(data)
        this.dataSource.paginator = this.paginator
      }
    )
  }

  patchVendor(vendor) {

    if (vendor) {
      vendor = this.vendors.find(data => data.id == vendor)
    }
    else {
      vendor = new Vendor()
    }
    const dialogRef = this.dialog.open(VendorFormComponent, {
      width: '1000px',
      data: vendor
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result && result == 'true') {
        this.loadVendors();
        this.snackBar.open(`Vendor Updated`, 'OK', {
          duration: 2000,
        })
      }
    })
    this.currentVendor = vendor
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
