import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { customer } from '../model/customer.model';
import { environment } from 'src/environments/environment.development';
// rxjs library f angular  li fiha tools li kay jigi dakchi asynchrone

@Injectable({
  providedIn: 'root'
  // kayn f la racine donc bla mat7awal declarih f module.app
})
export class CustomerService {


  constructor(private http: HttpClient) { }
  //an Observable object represents a source of asynchronous data
  public getCustomers(): Observable<Array<customer>> {
    return this.http.get<Array<customer>>(environment.backendHost + "/customer")
  }
  public searchCustomers(keyword: string): Observable<Array<customer>> {
    return this.http.get<Array<customer>>(environment.backendHost + "/customer/search?keyword=" + keyword)
  }
  public saveCustomer(customer: customer): Observable<customer> {
    return this.http.post<customer>(environment.backendHost + "/addcustomer", customer);
  }
  public deleteCustomer(id: number) {
    return this.http.delete(environment.backendHost + "/customers/" + id);
  }

}
