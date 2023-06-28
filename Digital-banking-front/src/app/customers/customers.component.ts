import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { CustomerService } from '../services/customer.service';
import { Observable, catchError, map, throwError } from 'rxjs';
import { customer } from '../model/customer.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers!: Observable<Array<customer>>; // customers ikad ikoun fiha any type 
  // variable : type dialha
  // Observable : data li radi tkoud  f obj customers radi tkoun de type observable
  errorMessage: Object | undefined; // 5asak tinitisializiha 
  // undefined il n'a pas de valeur 
  searchformGroup!: FormGroup;
  //FormBuilder used to create instance of FromGroup and FormControl 
  // FormGroup gerer les formulaires et leurs validation 
  constructor(private customerService: CustomerService, private fb: FormBuilder, private router: Router) { }
  ngOnInit(): void { // method qui s'execute au demarrage
    this.searchformGroup = this.fb.group({
      keyword: this.fb.control("")

    });
    // kaydir lik un formulaire li fih input field called keyword

    // formGroup contenaire dial les inputs dialouek , kola input katdir liha state dialha 


    this.customers = this.customerService.getCustomers().pipe(
      // pipe kaydir le traitement 3la kola objet kayji mn observable  
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );


    // .subscribe() b7al promise
    // katkoun m3a les methods asynchrone (observable)
    // katsna bima jaou les donnees et mn tmak katdir 3lihoum traitement
  }
  handleSearchCustomers() {

    let kw = this.searchformGroup?.value.keyword;
    // searchformGroup wa7d contenaire li fih l'ensemble dial les field dialoulek
    // les champs dialek , les valeurs diaoulek, les states dialek et leurs validations
    this.customers = this.customerService.searchCustomers(kw).pipe(
      // pipe kaydir le traitement 3la kola objet kayji mn observable  
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }
  handleDeleteCustomer(c: customer) {
    let conf = confirm("Are you sure ?");
    if (!conf) return;
    this.customerService.deleteCustomer(c.id).subscribe({
      next: (resp) => {
        this.customers = this.customers.pipe(
          map(data => {
            let index = data.indexOf(c);
            // kaymodifi l'array original
            data.slice(index, 1);
            // 1 mada5alch
            return data;
          })
        );
      }
    })

  }
  handleCustomerAccounts(cust: customer) {
    this.router.navigateByUrl("/customer-accounts/" + cust.id, { state: cust })
  }
}

