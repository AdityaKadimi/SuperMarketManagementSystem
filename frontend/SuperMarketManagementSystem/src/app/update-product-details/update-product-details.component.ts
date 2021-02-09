import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { HttpserviceService } from '../services/httpservice.service';
import { DataserviceService } from '../services/dataservice.service';
import { Product } from '../services/product';

@Component({
  selector: 'app-update-product-details',
  templateUrl: './update-product-details.component.html',
  styleUrls: ['./update-product-details.component.css']
})
export class UpdateProductDetailsComponent implements OnInit {

  message :any
  pid :any
  product :Product

  constructor(public snackBar: MatSnackBar, private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService ) {

  }

  ngOnInit() {
    this.dataservice.currentMessage.subscribe(message => this.message = message)
    this.dataservice.currentProductid.subscribe(pid => this.pid = pid)
    console.log("updated credentials details"+this.message)
    console.log("updated product details inside :"+this.pid)

    let credentials = {username: this.message.username, password: this.message.password};
    this.httpservice.getProductById(this.pid,credentials).subscribe(
      data => {
      this.product = data;
      console.log(this.product);
    },
    error => alert("error while retriving")
    );
  }

  updateProductToDb(){

    let credentials = {username: this.message.username, password: this.message.password};
    this.httpservice.updateproduct(this.product,credentials).subscribe(
      data => {
        alert("Product Updated sucessfully")
        this.router.navigate(['Admin-home'])
      console.log(data);
    },
    error => alert("error while retriving")
    );

  }

}
