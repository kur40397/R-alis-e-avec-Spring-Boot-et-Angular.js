import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AccountsService } from '../services/accounts.service';
import { Observable, catchError, throwError } from 'rxjs';
import { AccountDetails } from '../model/account.model';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accountFormgroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  //Observable == $ 
  accountObservable!: Observable<AccountDetails>
  OperationFormGroup!: FormGroup;
  errorMessage!: string
  constructor(private fb: FormBuilder, private accountService: AccountsService) { }
  ngOnInit(): void {
    this.accountFormgroup = this.fb.group({
      accountId: this.fb.control('')
    })
    this.OperationFormGroup = this.fb.group({
      operationType: this.fb.control(null),
      amount: this.fb.control(0),
      description: this.fb.control(null),
      accountDestination: this.fb.control(null)


    })
  }
  handleSearchAccount() {
    let accountId: string = this.accountFormgroup.value.accountId;
    this.accountObservable = this.accountService.getAccount(accountId, this.currentPage, this.pageSize).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err)
      })
    );
  }
  gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();
  }
  handleAccountOperation() {
    let accountId: string = this.accountFormgroup.value.accountId;
    let operationType: string = this.OperationFormGroup.value.operationType;
    let amount: number = this.OperationFormGroup.value.amount;
    let description: string = this.OperationFormGroup.value.description;
    let accountDestination: string = this.OperationFormGroup.value.accountDestination
    if (operationType == 'DEBIT') {
      this.accountService.debit(accountId, amount, description).subscribe({
        next: (data) => {
          alert("Success Debit")
          this.OperationFormGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err)
        }
      })
    } else if (operationType == 'CREDIT') {
      this.accountService.credit(accountId, amount, description).subscribe({
        next: (data) => {
          alert("Success credit")
          this.OperationFormGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err)
        }
      })
    } else if (operationType == 'TRANSFER') {

      this.accountService.transfer(accountId, accountDestination, amount, description).subscribe({
        next: (data) => {
          alert("Success Transfer")
          this.OperationFormGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err)
        }
      })
    }
  }
}
