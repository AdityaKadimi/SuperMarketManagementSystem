import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import { Product } from '../product';
import {MatTableDataSource} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {
  MatSnackBar,
  MatSnackBarConfig,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material';
import { Router } from '@angular/router';

import { error } from '@angular/compiler/src/util';
import { HttpserviceService } from '../services/httpservice.service';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';
import { SelectionModel } from '@angular/cdk/collections';


@Component({
  selector: 'app-update-products',
  templateUrl: './update-products.component.html',
  styleUrls: ['./update-products.component.css']
})
export class UpdateProductsComponent implements OnInit {
model = new Product();
 
constructor(public snackBar: MatSnackBar, private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService ) {

}
  
  unit = ['lb','kg','unit'];
  submitted = false;
  public products: Product[];
  message= new Logindetails();
  product :Product
  searchid: number;

  username = ''
  password = ''

  onSubmit() { this.submitted = true;
    //console.log(this.model.search2) 
  }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
   
    this.dataservice.currentMessage.subscribe(message => this.message = message)
    console.log("updated details"+this.message)
    let credentials = {username: this.message.username, password: this.message.password};
    
  
    this.httpservice.getAllProducts(credentials).subscribe(
      data => {
        this.products = data;
      this.dataSource = new MatTableDataSource<Product>(data); 
      this.dataSource.paginator = this.paginator;
      console.log(this.dataSource.data);
    },
    error => alert("error while retriving")
    );
  }
 
  displayedColumns: string[] = ['select', 'product_id', 'name', 'quantity', 'unit','price', 'description'];
  dataSource: MatTableDataSource<Product>;
  selection = new SelectionModel<Product>(false, []);

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
 
  searchProductById(){
   console.log("product id entered:"+this.searchid);
   let credentials = {username: this.message.username, password: this.message.password};
    this.httpservice.getProductById(this.searchid,credentials).subscribe(
      data => {
      this.product = data;
     
      console.log(this.product);
    },
    error => alert("error while retriving")
    );
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
  
  
    updateselectedproduct() {
      this.selection.selected.forEach(row => {
        console.log("product_id : "+row.product_id)
        this.dataservice.changeProductid(row.product_id)
     });
    
   }

 }
 
  
