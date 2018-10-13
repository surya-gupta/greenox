import { Customer } from "./customer";
import { Item } from "./item";

export class Order {
   
     id: string;
     orderNumber: number;
     status: string;
     customer : Customer;
     orderTime : Date;
     source : string;
     items : Item[];
     total: number;
     discount : number;
     netTotal: number;
     payment: number;
     note: string;
     cashier: string;
     server: string;
     serveTime: Date;
     
}
