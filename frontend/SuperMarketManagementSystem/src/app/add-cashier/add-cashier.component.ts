import { Component, OnInit } from '@angular/core';
import {
  MatSnackBar,
  MatSnackBarConfig,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material';
import { Router } from '@angular/router';
import { DataserviceService } from '../services/dataservice.service';
import { Cashier } from '../cashier';
import { HttpserviceService } from '../services/httpservice.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Logindetails } from '../logindetails';


@Component({
  selector: 'app-add-cashier',
  templateUrl: './add-cashier.component.html',
  styleUrls: ['./add-cashier.component.css']
})
export class AddCashierComponent implements OnInit {
 
  // form = new FormGroup({
  //   username: new FormControl('',Validators.required)
  // })

  cashier = new Cashier();
  didsplaymessage: string = 'Cashier Added';
  actionButtonLabel: string = 'OK';
  action: boolean = true;
  setAutoHide: boolean = true;
  autoHide: number = 2000;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  message= new Logindetails();
  
  
  username = ''
  password = ''
  invalidLogin = false

  addExtraClass: boolean = false;
  
  constructor(public snackBar: MatSnackBar, private router: Router, public dataservice : DataserviceService, public httpservice : HttpserviceService ) {

  }
   
  addcashier() {
    let config = new MatSnackBarConfig();
    config.verticalPosition = this.verticalPosition;
    config.horizontalPosition = this.horizontalPosition;
    config.duration = this.setAutoHide ? this.autoHide : 0;
    // config.extraClasses = this.addExtraClass ? ['test'] : undefined;
    //this.snackBar.open(this.didsplaymessage, this.action ? this.actionButtonLabel : undefined, config);

    console.log(this.cashier.name)
    console.log(this.cashier.username)
    console.log(this.cashier.email)
    console.log(this.cashier.contact)
    console.log(this.cashier.password)

    this.cashier.admin_id=1
    let credentials = {username: this.message.username, password: this.message.password};
    if(this.cashier.name != null && this.cashier.username != null && this.cashier.email != null && this.cashier.contact != null && this.cashier.password != null)
    {
      this.httpservice.addcashier(this.cashier,credentials).subscribe(data =>{
        this.cashier = data;
        console.log(this.cashier);
    alert("Cashier added Sucessfully")
        this.router.navigate(['Admin-home'])
      },
      error => alert("error while saving")
      );

    }

    else {
      alert("please enter valid details")
    }
    

  }

  ngOnInit() {
  
    this.dataservice.currentMessage.subscribe(message => this.message = message)
    console.log("updated details"+this.message)
  }

}