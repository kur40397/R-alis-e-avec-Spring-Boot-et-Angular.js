import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { customer } from '../model/customer.model';
import { environment } from 'src/environments/environment.development';
import { AccountDetails } from '../model/account.model';
import { AccountOperationDTO } from '../model/account.model'
// rxjs library f angular  li fiha tools li kay jigi dakchi asynchrone

@Injectable({
  providedIn: 'root'
  // kayn f la racine donc bla mat7awal declarih f module.app
})
export class AccountsService {

  constructor(private http: HttpClient) { }
  //an Observable object represents a source of asynchronous data
  public getAccount(accountId: string, page: number, size: number): Observable<AccountDetails> {
    ///accounts/{accountId}/pageOperation
    return this.http.get<AccountDetails>(environment.backendHost + "/accounts/" + accountId + "/pageOperations?page=" + page + "&size=" + size);
  }
  public debit(accountId: string, amount: number, description: string) {
    ///accounts/{accountId}/pageOperation
    let data = { id: accountId, amount: amount, description: description }

    return this.http.post(environment.backendHost + "/accounts/debit", data);
  }
  public credit(accountId: string, amount: number, description: string) {
    ///accounts/{accountId}/pageOperation
    let data = { id: accountId, amount: amount, description: description }
    return this.http.post(environment.backendHost + "/accounts/credit", data);
  }
  public transfer(accountSrc: string, accountDet: string, amount: number, description: string) {
    ///accounts/{accountId}/pageOperation
    let data = { accountSrc, accountDet, amount, description }
    console.log(accountDet)
    return this.http.post(environment.backendHost + "/accounts/transfer", data);
  }

}
