# GreenNox
Order Management

**REST API**

POST: localhost:8080/greennox/order/add

`{
 	"customer":{
 		"emailId":"surya@gmail.com"
 	},
 	"items":[
 		{"itemId":"TEA-1","name":"kulhad tea","quantity":1,"price":20,"comments":"strong"},
 		{"itemId":"SHAKE-1","name":"shakes","quantity":1,"price":59}],
 	"source":"Kiosk",
 	"payment":"Paytm",
 	"discount":10
 }`
 
 GET localhost:8080/greennox/order/update/5bb2412da1b60f54c04363ac/status/COMPLETED
 
 GET localhost:8080/greennox/order/open
 
 **Setup**

*mongodb.username* and *mongodb.password* as environment variable.