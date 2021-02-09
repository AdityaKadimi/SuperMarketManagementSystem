import { Component, OnInit } from '@angular/core';
import { CashierHomeComponent } from '../cashier-home/cashier-home.component';
import { AuthserviceService} from '../services/authservice.service';
import { Cashierlogin } from '../cashierlogin';
import { Router } from '@angular/router';
import { DataserviceService } from '../services/dataservice.service';

@Component({
  selector: 'app-cashier',
  templateUrl: './cashier.component.html',
  styleUrls: ['./cashier.component.css']
})
export class CashierComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false

  flag : boolean = false

  constructor(private router: Router,
    private loginservice: AuthserviceService,
    private data : DataserviceService) { }

  ngOnInit() {}

checkLogin() {

  let credentials = {username: this.username, password: this.password};
    this.loginservice.authenticate(credentials, () => {
      this.invalidLogin = false;
      this.flag=true
      this.data.changeMessage(credentials)
      this.router.navigate(['Cashier-home'])
    },
    );
   
    this.invalidLogin = true
    return false;


}
}
