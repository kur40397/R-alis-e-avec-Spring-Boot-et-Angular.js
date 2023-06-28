import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './accounts/accounts.component';// kandirou l'appel de la partie type script
import { CustomersComponent } from './customers/customers.component'
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { CustomerAccountsComponent } from './customer-accounts/customer-accounts.component';
const routes: Routes = [
  { path: "customers", component: CustomersComponent },
  { path: "accounts", component: AccountsComponent },
  { path: "new-customer", component: NewCustomerComponent },
  { path: "customer-accounts/:id", component: CustomerAccountsComponent }

];
// un decorator bach tconfiguri le module li fih nta kayzid des fonctionnalite zaydin et kaychangi le compotement
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  // importinah
  //  le system de routage RouterModule  va prendre en consideration routes
  exports: [RouterModule]
})
export class AppRoutingModule { }
