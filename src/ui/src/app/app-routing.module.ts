import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersComponent } from './users/users.component';
import { DetailsComponent } from './details/details.component';
import { OrdersComponent } from './orders/orders.component';
import {PlaceOrderComponent} from './place-order/place-order.component';
const routes: Routes = [
  {
    path: '',
    component: UsersComponent
  },
  {
    path: 'details/:id',
    component: DetailsComponent
  },
  {
    path: 'orders',
    component: OrdersComponent
  },{
    path: 'placeOrder',
    component: PlaceOrderComponent
   }
  
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }