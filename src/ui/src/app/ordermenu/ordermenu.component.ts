import { Component, OnInit, Inject,Renderer2 } from '@angular/core';
import {MenuService} from '../menu.service'
 

 import { DOCUMENT } from '@angular/common';
import {ViewChild, ElementRef} from '@angular/core';
import { Category } from './Category';
import { Item } from './Item';
@Component({
  selector: 'app-ordermenu',
  templateUrl: './ordermenu.component.html',
  styleUrls: ['./ordermenu.component.scss']
})
export class OrdermenuComponent implements OnInit {
  cartItem:Item[]=[];
  totalPrice:number=0;
  currentCategory:string;
   
  constructor (private menuService:MenuService,  @Inject(DOCUMENT) private document: Document,
    private renderer: Renderer2) { }
   
catSelect(id:string,event:any):void{
  this.currentCategory=id;
  event.preventDefault();
  //this.document.body;
  this.document.getElementById(id).scrollIntoView({ block: 'start',  behavior: 'smooth' });
  this.currentCategory=id;

}
addMenu(item:Item,quantity:number):void{
  
  item.quantity=item.quantity+quantity;
  var singleItem:Item=new Item();
  singleItem=this.cartItem.find(o=>o.id==item.id);
  if(!singleItem){
    this.cartItem.push(item);
  }
  if(item.quantity<=0){
    item.quantity=0;
    var index=this.cartItem.indexOf(singleItem);
    this.cartItem.splice(index,1);
  }
 
  this.totalPrice=this.totalPrice+quantity*item.price;
}
@ViewChild('firstNameInput') nameInputRef: ElementRef;
show(lastName: HTMLInputElement){
  
  this.nameInputRef.nativeElement.value + ' ' + lastName.value;
}

  ngOnInit() {
    this.menuService.getMenuList();
   
  }

  

}
