import { Component, OnInit } from '@angular/core';
import {
  MatSnackBar,
  MatSnackBarConfig,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material';
import { Router } from '@angular/router';
import { Product } from '../product';

import { error } from '@angular/compiler/src/util';
import { HttpserviceService } from '../services/httpservice.service';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';


@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  public products: Product[];
  message= new Logindetails();
  product= new Product();
 
  username = ''
  password = ''
  invalidLogin = false

  flag : boolean = false

  mess: string = 'Product Added';
  actionButtonLabel: string = 'OK';
  action: boolean = true;
  setAutoHide: boolean = true;
  autoHide: number = 2000;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  
  addExtraClass: boolean = false;
  unitvalues : string[] = ['lb','gallon','unit','piece']
 
constructor(public snackBar: MatSnackBar, private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService ) {

  }
   addproduct() {
    let config = new MatSnackBarConfig();
    config.verticalPosition = this.verticalPosition;
    config.horizontalPosition = this.horizontalPosition;
    config.duration = this.setAutoHide ? this.autoHide : 0;
    // config.extraClasses = this.addExtraClass ? ['test'] : undefined;
    //this.snackBar.open(this.mess, this.action ? this.actionButtonLabel : undefined, config);
    console.log(this.product.name)
    console.log(this.product.description)
    console.log(this.product.price)
    console.log(this.product.quantity)
    console.log(this.product.unit)
    // if(this.product.name=null){
    //   this.product.id =0;
    // }
    // else{
    //   this.product.id =1;
    // }
    
   // console.log(this.product)
   // this.productdata.changeMessage(this.product)
   console.log("product id : "+this.product.product_id)
   let credentials = {username: this.message.username, password: this.message.password};
if( this.product.name!=null && this.product.description !=null && this.product.price != null && this.product.quantity != null){

  this.httpservice.addproduct(this.product,credentials).subscribe(data =>{
    this.product = data;
    console.log(this.product);
    alert("Product added sucessfully")
    this.router.navigate(['Admin-home'])
  },
  error => alert("error while saving")
  );

  this.httpservice.getAllProducts(credentials).subscribe(
    data => {
    this.products = data;
    console.log(this.products);
  },
  error => alert("error while retriving")
  );
  console.log("helo");
  }

  else {
    alert("Please enter valid details")
  }
   
  }
 
  ngOnInit() {
   
    this.dataservice.currentMessage.subscribe(message => this.message = message)
    console.log("updated details"+this.message)
  }

}