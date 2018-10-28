import { DataService } from 'src/app/data.service';
import { Vendor } from 'src/app/shared/vendor';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { MatDialog, MatSnackBar } from '@angular/material';
import { InventoryOrder } from 'src/app/shared/inventory-order';
import { InventoryUpdateComponent } from '../update/update.component';
@Component({
  selector: 'app-inventory-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class InventoryViewComponent implements OnInit {

  inventoryOrders: InventoryOrder[]
  currentOrder: InventoryOrder
  dataSource = new MatTableDataSource(this.inventoryOrders);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  pageSizeOptions: number[] = [5, 10, 25, 50];
  displayedColumns: string[] = ['entryTime', 'invNum', 'vendor.name', 'vendor.phoneNumber', 'advanceAmount', 'netAmount', 'pendingAmount', 'paymentMode', 'status', 'completionTime', 'id'];
  constructor(private data: DataService, public dialog: MatDialog, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.loadInventoryOrders()
  }
  loadInventoryOrders() {
    this.data.getInventoryOrders({}).subscribe(
      data => {
        this.inventoryOrders = data
        this.dataSource = new MatTableDataSource(data)
        this.dataSource.paginator = this.paginator
      }
    )
  }
  patchVendor(order) {

    if (order) {
      order = this.inventoryOrders.find(data => data.id == order)
    }
    else {
      return
    }
    const dialogRef = this.dialog.open(InventoryUpdateComponent, {
      width: '1000px',
      data: order
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result && result == 'true') {
        this.loadInventoryOrders();
        this.snackBar.open(`Order Updated`, 'OK', {
          duration: 2000,
        })
      }
    })
    this.currentOrder = order
  }
}