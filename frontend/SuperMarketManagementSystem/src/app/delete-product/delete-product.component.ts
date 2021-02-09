import { Component , ViewChild,OnInit} from '@angular/core';
import {MatPaginator, MatTableDataSource, MatSnackBar } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Product } from '../product';
import { Router } from '@angular/router';
import { HttpserviceService } from '../services/httpservice.service';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';


@Component({
  selector: 'app-delete-product',
  templateUrl: './delete-product.component.html',
  styleUrls: ['./delete-product.component.css']
})

export class DeleteProductComponent implements OnInit {

  displayedColumns: string[] = ['select', 'product_id', 'name', 'quantity', 'price','unit','description'];
  dataSource: MatTableDataSource<Product>;
  selection = new SelectionModel<Product>(false, []);
 
  public products: Product[];
  message= new Logindetails();
  product :Product
  searchid: number;

  username = ''
  password = ''

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  
  model = new Product();
  submitted = false;
  constructor(public snackBar: MatSnackBar, private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService ) {
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  
  onSubmit() { this.submitted = true;
    //console.log(this.model.search2)
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


  removeSelectedRows() {
    this.selection.selected.forEach(row => {
      console.log(row.product_id)
      let credentials = {username: this.message.username, password: this.message.password};
      this.httpservice.deleteProduct(row.product_id,credentials).subscribe(
        data => {
          alert("Product Deleted Sucessfully")
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
     
      },
      error => alert("error while retriving")
    );
      
   });
  
 }

}

