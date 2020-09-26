import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

const api_Java = 'http://localhost:9000/api/v1/';
//const api_Java = '/api/v1/';
const cabecera = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'})

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private http: HttpClient) {}

    paymentSave(data): Observable<any> {
      return this.http.post(api_Java + 'payment',data,{ headers: cabecera});
    }



   
}
