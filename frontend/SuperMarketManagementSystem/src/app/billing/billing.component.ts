import { Component, OnInit, ViewChild } from '@angular/core';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import { Router } from '@angular/router';
import { HttpserviceService } from '../services/httpservice.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { Product } from '../product';
import { Bill } from '../bill';
import { SelectionModel } from '@angular/cdk/collections';

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.css']
})
export class BillingComponent implements OnInit {
  submitted = false;
  today: Date = new Date();
  
  message= new Logindetails();

  public products: Product[];

 // product = new Product();
  bill = new Bill();
  productid: number;

  username = ''
  password = ''  

  unitvalues : string[] = ['lb','gallon','unit','piece']
  selection = new SelectionModel<Bill>(false, []);

  constructor(private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService ) {

  }

  ngOnInit() {
     
  }
  
  onSubmit() {
    this.submitted = true;
  }

  
  productArray= [];
  total : number = 0;
  itemtotal : number = 0;
  dataSource: MatTableDataSource<Bill>;
  oldquantity :number;
  
  
  findproductByid(){
    this.dataservice.currentMessage.subscribe(message => this.message = message)
    console.log("updated details"+this.message)

    let credentials = {username: this.message.username, password: this.message.password};

    this.httpservice.getProductById(this.productid,credentials).subscribe(
      data => {
        this.bill = data;
        console.log(this.bill.name)
      
    },
    error => alert("error while retriving")
    );

  }

  addtocart(){
    let canInsert = 1;
    console.log("oldquantity: "+this.oldquantity)
    console.log("newquantity: "+this.bill.quantity)
 if(Number(this.bill.quantity) > Number(this.oldquantity)){
  console.log("oldquantity: "+this.oldquantity)
  console.log("newquantity: "+this.bill.quantity) 
  canInsert = 0;
  alert("item Out Of Stock")
 }
    this.productArray.forEach((item) => {
      if(item.product_id == this.bill.product_id)
      {
        canInsert = 0;
      }
    })
    if(canInsert==1)
   { 
     
     this.itemtotal= Number(this.bill.price) * Number(this.bill.quantity);
     this.bill.totalprice=this.itemtotal;
     (this.total) = this.total + Number(this.bill.price) * Number(this.bill.quantity);
     console.log(typeof this.total);
     this.productArray.push(this.bill);
    console.log("after push"+this.productArray);
    this.dataSource = new MatTableDataSource<Bill>(this.productArray); 
    this.dataSource.paginator = this.paginator;
   }
  }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
 // displayedColumns: string[] = ['select','name','quantity','unitprice','totalprice'];
  displayedColumns: string[] = ['name','quantity','unitprice','totalprice'];
  
  /** Gets the total cost of all transactions. */
  getTotalCost() {

    return this.productArray.map(t => t.price).reduce((acc, value) => acc + value, 0);
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }


  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  // checkboxLabel(row?: PeriodicElement): string {
  //   if (!row) {
  //     return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
  //   }
  //   return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  // }
  resetAllfeilds(){
    // this.bill.name=''
    // this.bill.price=null;
    // this.bill.quantity=null;
    // this.bill.unit=null;
    // this.productid=null;
  }

  Checkoutpayment(){
    this.dataservice.currentMessage.subscribe(message => this.message = message)
    console.log("updated details"+this.message)

    let credentials = {username: this.message.username, password: this.message.password};

    this.httpservice.saveTransaction(this.productArray,credentials).subscribe(
      data => {
       alert("Transaction Sucesses..!!")
        //console.log(this.bill.name)
        this.router.navigate(['Cashier-home'])
        
   
    },
    error => alert("error while retriving")
    );

  }

  autofind(pid: number) {
   // this.dataSource.filter = filterValue.trim().toLowerCase();
   this.dataservice.currentMessage.subscribe(message => this.message = message)
   console.log("updated details"+this.message)

   let credentials = {username: this.message.username, password: this.message.password};

   this.httpservice.getProductById(pid,credentials).subscribe(
     data => {
       this.bill = data;
       console.log(this.bill.name)
       this.oldquantity= this.bill.quantity
       console.log("initial quantity :"+this.bill.quantity)
   }
  //  ,
  //  error =>
  //   alert("error while retriving")
   );
  }

  Voidtransaction() {

    this.productArray=[];
    this.dataSource = new MatTableDataSource<Bill>(this.productArray); 
   this.dataSource.paginator = this.paginator;
   this.total=0;

  //   this.selection.selected.forEach(row => {
  //     console.log(row.product_id)

  //     this.productArray.forEach((item) => {
  //       if(item.product_id == row.product_id)
  //       {
  //        this.productArray.splice(item);
  //        this.total = this.total - Number(item.price) * Number(item.quantity);
  //        this.dataSource = new MatTableDataSource<Bill>(this.productArray); 
  //        this.dataSource.paginator = this.paginator;
  //       }
  //     })
      
  //  });
  //  console.log("After removal :"+this.productArray)
 }

}
