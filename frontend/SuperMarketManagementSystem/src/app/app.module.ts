import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import {RouterModule} from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { CashierComponent } from './cashier/cashier.component';
import { HomeComponent } from './home/home.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddCashierComponent } from './add-cashier/add-cashier.component';
import {MatTableModule} from '@angular/material/table';

import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatChipsModule} from '@angular/material/chips';
import {MatStepperModule} from '@angular/material/stepper';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSortModule} from '@angular/material/sort';
import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTreeModule} from '@angular/material/tree';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AddProductComponent } from './add-product/add-product.component';
import { CashierHomeComponent } from './cashier-home/cashier-home.component';
import { AdminLogoutComponent } from './admin-logout/admin-logout.component';
import { UpdateProductsComponent } from './update-products/update-products.component';
import { DeleteProductComponent } from './delete-product/delete-product.component';
import { DeleteCashierComponent } from './delete-cashier/delete-cashier.component';
import { UpdateProductDetailsComponent } from './update-product-details/update-product-details.component';
import { ViewTransactionsComponent } from './view-transactions/view-transactions.component';
import { BillingComponent } from './billing/billing.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    CashierComponent,
    HomeComponent,
    AdminHomeComponent,
    AddCashierComponent,
    AddProductComponent,
    CashierHomeComponent,
    AdminLogoutComponent,
    UpdateProductsComponent,
    DeleteProductComponent,
    DeleteCashierComponent,
    UpdateProductDetailsComponent,
    ViewTransactionsComponent,
    BillingComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatSnackBarModule, BrowserAnimationsModule, MatSelectModule, MatCheckboxModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatSelectModule,
    NgbModule,
    DragDropModule,
    RouterModule.forRoot([ 
{
  path:'',
  component: HomeComponent
},
{
  path:'Cashier',
  component: CashierComponent
},
{
  path:'Admin',
  component: AdminComponent
},
{
  path: 'Admin-home',
  component: AdminHomeComponent
},
{
  path: 'addProduct',
  component: AddProductComponent
},
{
  path: 'addCashier',
  component: AddCashierComponent
},
{
  path: 'Cashier-home',
  component: CashierHomeComponent
},
{
  path: 'admin-logout',
  component: AdminLogoutComponent
},
{
  path: 'delete-product',
  component: DeleteProductComponent
},
{
  path: 'delete-product-detalis',
  component: UpdateProductDetailsComponent
}


    ])
    
  ],
  exports:[ MatTableModule ],
  providers: [MatTableModule,NgbModule
              ],
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}