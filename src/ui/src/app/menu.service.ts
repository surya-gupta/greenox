import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Menu} from './ordermenu/Menu'
import { Category } from './ordermenu/category';
import {Item} from './ordermenu/Item'
@Injectable({
  providedIn: 'root'
})
export class MenuService {
  orderMenu:Menu["menus"][]=[];
  menuList:Category[]=[];
  categoryList:Item[]=[];
  itemList:Item[]=[];
  itemGroupList:Item[]=[];
  
 
  constructor(private httpClient:HttpClient) { }

  getMenuJSON():Observable<Menu[]>{
   return this.httpClient.get<Menu[]>('src/app/menu.json');
  
  }

  getMenuList():void{
    this.getMenuJSON().subscribe(o=>{
        
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


}
