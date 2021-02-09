import { Component, OnInit, ViewChild } from '@angular/core';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';
import { MatPaginator, MatSnackBar, MatTableDataSource } from '@angular/material';
import { Product } from '../product';
import { Router } from '@angular/router';
import { HttpserviceService } from '../services/httpservice.service';
import { SelectionModel } from '@angular/cdk/collections';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  message= new Logindetails();

  public products: Product[];

  product :Product
  searchid: number;

  username = ''
  password = ''
  
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(public snackBar: MatSnackBar, private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService ) {

  }

  displayedColumns: string[] = ['product_id', 'name', 'quantity', 'unit' ,'price', 'description'];
  dataSource: MatTableDataSource<Product>;
  selection = new SelectionModel<Product>(false, []);

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
 

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

  loginAdminhome(event){
    console.log(event)
    
  }
}