import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLogoutComponent } from './admin-logout/admin-logout.component';
import { HomeComponent } from './home/home.component';
import { CashierComponent } from './cashier/cashier.component';
import { AdminComponent } from './admin/admin.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AddCashierComponent } from './add-cashier/add-cashier.component';
import { CashierHomeComponent } from './cashier-home/cashier-home.component';
import { AuthguardService } from './services/authguard.service';
import { UpdateProductsComponent } from './update-products/update-products.component';
import { DeleteProductComponent } from './delete-product/delete-product.component';
import { UpdateProductDetailsComponent } from './update-product-details/update-product-details.component';
import { DeleteCashierComponent } from './delete-cashier/delete-cashier.component';
import { BillingComponent } from './billing/billing.component';
import { ViewTransactionsComponent } from './view-transactions/view-transactions.component';


const routes: Routes = [
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
    component: AdminHomeComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'addProduct',
    component: AddProductComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'addCashier',
    component: AddCashierComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'Cashier-home',
    component: CashierHomeComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'admin-logout',
    component: AdminLogoutComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'updateProduct',
    component: UpdateProductsComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'deleteProduct',
    component: DeleteProductComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'deleteCashier',
    component: DeleteCashierComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'updateProductDetails',
    component: UpdateProductDetailsComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'billingDetails',
    component: BillingComponent,
    canActivate:[AuthguardService]
  },
  {
    path: 'view-transactionDetails',
    component: ViewTransactionsComponent,
    canActivate:[AuthguardService]
  }
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
