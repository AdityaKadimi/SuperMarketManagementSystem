import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminHomeComponent } from '../admin-home/admin-home.component';
import { CashierComponent } from '../cashier/cashier.component';
import { AuthserviceService } from '../services/authservice.service';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false
  message= new Logindetails();
  flag : boolean = false

  constructor(private router: Router,
    private loginservice: AuthserviceService,
    private data: DataserviceService) { }

  ngOnInit() {

    // this.data.currentMessage.subscribe(message => this.message = message)
    // console.log("updated details"+this.message)
  }
   
// checkLogin() {
//   if (this.loginservice.authenticate(this.username, this.password)
//   ) {
//     this.router.navigate(['Admin-home'])
//     this.invalidLogin = false
//   } else
//     this.invalidLogin = true
// }

checkLogin() {

  let credentials = {username: this.username, password: this.password};


  if(this.username != null && this.password != null){

    this.loginservice.authenticate(credentials, () => {
      this.invalidLogin = false;
      this.flag=true
      console.log("credentials in admin login"+ credentials)
      this.data.changeMessage(credentials)
      this.router.navigate(['Admin-home'])
    },
    );
   
    this.invalidLogin = true
    return false;

  }
  
  else{
    alert("Please enter Username or Password")
  }

}

sendcredentials(){
  
}

}