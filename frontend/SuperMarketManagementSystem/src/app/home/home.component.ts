import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminComponent } from '../admin/admin.component';
import { CashierComponent } from '../cashier/cashier.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  

   constructor(private router: Router) { }

  ngOnInit() {
    function goToPage(pageName:AdminComponent){
      this.router.navigate([`${AdminComponent}`]);
      
    }
    function goToPage1(pageName:CashierComponent){
      this.router.navigate([`${CashierComponent}`]);

  }

  


  
  }

}