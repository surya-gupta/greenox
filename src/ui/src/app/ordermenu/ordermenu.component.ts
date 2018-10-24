import { Component, OnInit, Inject,Renderer2 } from '@angular/core';
import {MenuService} from '../menu.service'
import {Menu} from './Menu'
import { Category } from './category';
import {Item} from './Item'
 import { DOCUMENT } from '@angular/common';
import {ViewChild, ElementRef} from '@angular/core';
@Component({
  selector: 'app-ordermenu',
  templateUrl: './ordermenu.component.html',
  styleUrls: ['./ordermenu.component.scss']
})
export class OrdermenuComponent implements OnInit {
orderMenu:Menu["menus"][]=[];
menuList:Category[]=[];
categoryList:Item[]=[];
itemList:Item[]=[];
itemGroupList:Item[]=[];
currentCategory:string;

   
  constructor (private menuService:MenuService,  @Inject(DOCUMENT) private document: Document,
    private renderer: Renderer2) { }
getMenuList():void{
    this.menuService.getMenuJSON().subscribe(o=>{
        
      this.orderMenu["menus"]=o;
      
      o["menus"].forEach(element => {
        var menu=new Category();
        menu.id=element.menu.id;
        menu.name=element.menu.name;
        menu.items=[];
        this.menuList.push(menu);
        element.menu.categories.forEach(eleCat => {
          var cat=new Item();
          cat.pid=menu.id;
          cat.id=eleCat.category.id;
          cat.name=eleCat.category.name;
          
          this.categoryList.push(cat);
          menu.items.push(cat);
          cat.item=[];
          eleCat.category.items.forEach(eleItem => {
            var item=new Item();
            item.pid=cat.id;
            item.id=eleItem.item.id;
            item.name=eleItem.item.name;
            item.price=eleItem.item.price;
            item.quantity=0;
            this.itemList.push(item);
            cat.item.push(item);
            item.item=[];
            if(eleItem.item.groups){
              eleItem.item.groups.forEach(eleGroup => {
                
                eleGroup.group.items.forEach(eleGroupItem => {
                  var itemGroup=new Item();
                  itemGroup.pid=item.id;
                  itemGroup.id=eleGroupItem.item.id;
                  itemGroup.name= eleGroup.group.name+'-'+eleGroupItem.item.name;
                  itemGroup.price=eleGroupItem.item.price;
                  this.itemGroupList.push(itemGroup);
                  item.item.push(itemGroup);
                  
                });
              });
            }
          
          });


        });
        
      });
      
    console.log(o["menus"]); 
    console.log(this.menuList); 
    console.log(this.categoryList); 
    console.log(this.itemList); 
    console.log(this.itemGroupList); 

        

    });
 
}
catSelect(id:string,event:any):void{
  this.currentCategory=id;
  event.preventDefault();
  //this.document.body;
  this.document.getElementById(id).scrollIntoView({ block: 'start',  behavior: 'smooth' });
  this.currentCategory=id;

}
@ViewChild('firstNameInput') nameInputRef: ElementRef;
show(lastName: HTMLInputElement){
  
  this.nameInputRef.nativeElement.value + ' ' + lastName.value;
}
  ngOnInit() {
    this.getMenuList();
   
  }

  

}
