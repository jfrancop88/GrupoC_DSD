import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

const api_Java = 'http://localhost:9000/api/v1/';
const cabecera = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'})

@Injectable({
  providedIn: 'root'
})
export class EcommerceService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any>{
    return this.http.get(api_Java + 'payment');
  }

  getPaymentDetails(id): Observable<any> {
    return this.http.get(api_Java + 'payment/'+id);
  }
  
}
