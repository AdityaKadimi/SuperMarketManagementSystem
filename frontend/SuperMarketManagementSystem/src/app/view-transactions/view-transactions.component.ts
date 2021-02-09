import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { HttpserviceService } from '../services/httpservice.service';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';
import { Transaction } from '../transaction';
import { Variable } from '@angular/compiler/src/render3/r3_ast';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-view-transactions',
  templateUrl: './view-transactions.component.html',
  styleUrls: ['./view-transactions.component.css']
})
export class ViewTransactionsComponent implements OnInit {

  constructor(private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService ) {

  }

  ngOnInit() {
   
  }

  selectedDate1 : Date;
  selectedDate2 : Date;
 
  message= new Logindetails();
  username = ''
  password = ''

  maxDate = new Date();

  total=0;

 transaction = new Transaction()

 transactions = [];

 dataSource: MatTableDataSource<Transaction>;
 
 @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
 displayedColumns: string[] = ['productname','quantity','unitprice','totalprice','cashiername'];
 
 /** Gets the total cost of all transactions. */
 getTotalCost() {

   return this.transactions.map(t => t.totalprice).reduce((acc, value) => acc + value, 0);

 }


  
getTransactionsAsPerDate(){
let newdate1 =this.selectedDate1.toLocaleDateString();
let newdate2 = this.selectedDate2.toLocaleDateString()
let finaldate1= newdate1.split("/").join("-")
let finaldate2= newdate2.split("/").join("-")
console.log("From Date: "+this.selectedDate1.toLocaleDateString());
console.log("To Date: "+this.selectedDate2.toLocaleDateString());
console.log("final date1: "+ finaldate1);
console.log("Final date2 :"+ finaldate2)


this.dataservice.currentMessage.subscribe(message => this.message = message)
console.log("updated details"+this.message)

let credentials = {username: this.message.username, password: this.message.password};

this.httpservice.getTransactionsAsPerDate(finaldate1,finaldate2,credentials).subscribe(
  data => {
    this.transactions = data;
    // if(this.transactions=null){
    //   this.total=null;
    // }
    console.log(this.transactions);
    this.dataSource = new MatTableDataSource<Transaction>(this.transactions); 
    this.dataSource.paginator = this.paginator;

    this.transactions.forEach(item => {
      console.log("item price"+item.totalprice);
      (this.total) = this.total + Number(item.totalprice);
      
    });

    console.log("total amount :"+this.total);
},
error => alert("error while retriving")
);

}


}
