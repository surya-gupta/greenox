import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersComponent } from './users/users.component';
import { DetailsComponent } from './details/details.component';
import { OrdersComponent } from './orders/orders.component';
 import {OrdermenuComponent} from './ordermenu/ordermenu.component';
import { InventoryComponent } from './inventory/inventory.component';
 
const routes: Routes = [
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'inventory',
    component: InventoryComponent
  },
  {
    path: 'details/:id',
    component: DetailsComponent
  },
  {
    path: 'ordermenu',
    component: OrdermenuComponent
  },

  {
    path: '',
    component: OrdersComponent
  } 
  
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }