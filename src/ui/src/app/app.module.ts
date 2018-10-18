import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { SidebarComponent } from './sidebar/sidebar.component';
import { OrdersComponent } from './orders/orders.component';
import { UsersComponent } from './users/users.component';
import { DetailsComponent } from './details/details.component'; 
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material';
import { GetValuesPipe } from './shared/GetValuesPipe';
import { InventoryComponent } from './inventory/inventory.component';
import { InventoryOrderComponent } from './inventory/order/order.component';
import { InventoryViewComponent } from './inventory/view/view.component';
import { InventoryUpdateComponent } from './inventory/update/update.component';
import { InventoryReportsComponent } from './inventory/reports/reports.component';
import { InventoryVendorsComponent } from './inventory/vendors/vendors.component';
import { OrderOpenComponent } from './orders/open/open.component';
import { OrderCreateComponent } from './orders/create/create.component';
import { OrderReportsComponent } from './orders/reports/reports.component';
import { OrderUpdateComponent } from './orders/update/update.component';
import { OrderViewComponent } from './orders/view/view.component';



@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    OrdersComponent,
    UsersComponent,
    DetailsComponent,
    GetValuesPipe,
    InventoryComponent,
    InventoryOrderComponent,
    InventoryViewComponent,
    InventoryUpdateComponent,
    InventoryReportsComponent,
    InventoryVendorsComponent,
    OrderOpenComponent,
    OrderCreateComponent,
    OrderReportsComponent,
    OrderUpdateComponent,
    OrderViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
