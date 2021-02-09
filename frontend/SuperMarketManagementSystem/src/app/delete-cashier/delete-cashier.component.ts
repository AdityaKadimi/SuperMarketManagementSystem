import { Component , ViewChild,OnInit, Inject} from '@angular/core';
import {MatPaginator, MatTableDataSource, MatSnackBar, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Product } from '../product';
import { Router } from '@angular/router';
import { HttpserviceService } from '../services/httpservice.service';
import { DataserviceService } from '../services/dataservice.service';
import { Logindetails } from '../logindetails';
import { Cashier } from '../cashier';
import { ConfirmationDialogService } from '../confirmation-dialog.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-delete-cashier',
  templateUrl: './delete-cashier.component.html',
  styleUrls: ['./delete-cashier.component.css']
})
export class DeleteCashierComponent implements OnInit {

  displayedColumns: string[] = ['select', 'cashier_id', 'username', 'name', 'email', 'contact','password'];
  dataSource: MatTableDataSource<Cashier>;
  selection = new SelectionModel<Cashier>(false, []);
 
  public cashiers: Cashier[];
  message= new Logindetails();
  cashier : Cashier
  searchid: number;

  username = ''
  password = ''

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  
  model = new Cashier();
  submitted = false;

  constructor(public snackBar: MatSnackBar, private router: Router, public httpservice : HttpserviceService, public dataservice : DataserviceService,public dialog: MatDialog,private confirmationDialogService: ConfirmationDialogService ) {
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
    this.httpservice.getAllCashiers(credentials).subscribe(
      data => {
      this.cashiers = data;
      this.dataSource = new MatTableDataSource<Cashier>(data); 
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
      console.log(row.cashier_id)
      let credentials = {username: this.message.username, password: this.message.password};
      this.httpservice.deleteCashier(row.cashier_id,credentials).subscribe(
        data => {
          alert("Cashier Deleted Sucessfully")
          let credentials = {username: this.message.username, password: this.message.password};
          this.httpservice.getAllCashiers(credentials).subscribe(
            data => {
            this.cashiers = data;
            this.dataSource = new MatTableDataSource<Cashier>(data); 
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

 openConfirmationDialog() {
   console.log("insidefunction");
  this.confirmationDialogService.confirm('Please confirm..', 'Do you really want to ... ?')
  .then((confirmed) => console.log('User confirmed:', confirmed))
  .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));
}
clickMethod() {
  
  this.selection.selected.forEach(row => {
    console.log(row.cashier_id)
    let credentials = {username: this.message.username, password: this.message.password};
    if(confirm("Are you sure to delete "+row.name)) {
      console.log("Implement delete functionality here");
      
      this.httpservice.deleteCashier(row.cashier_id,credentials).subscribe(
        data => {
          alert("Cashier Deleted Sucessfully")
          let credentials = {username: this.message.username, password: this.message.password};
          this.httpservice.getAllCashiers(credentials).subscribe(
            data => {
            this.cashiers = data;
            this.dataSource = new MatTableDataSource<Cashier>(data); 
            this.dataSource.paginator = this.paginator;
            console.log(this.dataSource.data);
          },
          error => alert("error while retriving")
          );
     
      },
      error => alert("error while retriving")
    );

    }
   
    
 });

}

}

