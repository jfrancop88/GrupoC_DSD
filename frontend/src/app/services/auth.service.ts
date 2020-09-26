import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

const api_Node = 'http://localhost:4545/';
const api_Java = 'http://localhost:9000/api/v1/';

const cabecera = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'})


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  register(registro): Observable<any> {
    return this.http.post(api_Node + 'register',registro,{ headers: cabecera});
  }

  signin(login): Observable<any> {
    return this.http.post(api_Node + 'login',login,{ headers: cabecera});
  }

  getUsers(): Observable<any> {
    return this.http.get(api_Node + 'users');
  }

  registerCommerce(comercio): Observable<any> {
    return this.http.post(api_Java + 'ecommerce',comercio,{ headers: cabecera});
  }



}
