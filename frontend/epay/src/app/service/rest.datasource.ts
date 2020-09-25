import {Injectable} from '@angular/core';
import {Http, Request, RequestMethod} from '@angular/http';

import {Observable} from 'rxjs/Observable';


import 'rxjs/add/operator/map';
import {Body} from '@angular/http/src/body';
import {headersToString} from 'selenium-webdriver/http';
//import { element } from '@angular/core/src/render3';


// Chequea la ruta a modificar con mongodb
const AUTH_URL = 'http://localhost:4545/';
const API_URL = 'http://localhost:3800/private/';
const LS_KEY = 'currentUser';

@Injectable()
export class RestDataSource {
  auth_token: string;

// tslint:disable-next-line: deprecation
  constructor(private http: Http) {
    const currentUser = JSON.parse(localStorage.getItem(LS_KEY));
    if (currentUser) {
      this.auth_token = currentUser.token;
    }
  }

// tslint:disable-next-line: deprecation
  private sendRequest(verb: RequestMethod, path: string, body: any, auth: boolean = false): Observable<any> {
    const request = new Request({
      method: verb,
      url: API_URL + path,
      body: body
    });
    if (auth && this.auth_token) {
      request.headers.set('Authorization', `Bearer ${this.auth_token}`);
    }
    return this.http.request(request).map(response => response.json());

  }

  signin(username: string, password: string): Observable<boolean> {
    const request = new Request({
      method: RequestMethod.Post,
      url: AUTH_URL + 'login',
      body: {username, password}
    });
    // console.log(request);
    return this.http.request(request).map(response => {
      // login successful if there's a jwt token in the response
      const json = response.json();
      const token = json.token;
      if (token) {
        this.auth_token = token;
        localStorage.setItem(LS_KEY, JSON.stringify({username, token, id: json.id, codigo: json.codigo}));
        // return true to indicate successful login
        return true;
      } else {
        // return false to indicate failed login
        return false;
      }
    });
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    this.auth_token = null;
    localStorage.removeItem(LS_KEY);
  }

  signup(email: string, password: string): Observable<boolean> {
    return this.http.request(new Request({
      method: RequestMethod.Post,
      url: AUTH_URL + 'signup',
      body: {email, password}
    })).map(response => {
      const json = response.json();
      const token = json.token;
      if (token) {
        this.auth_token = token;
        localStorage.setItem(LS_KEY, JSON.stringify({email, token, id: json.id}));
        // return true to indicate successful login
        return true;
      } else {
        // return false to indicate failed login
        return false;
      }
    });
  }

  forgotPassword(email: string): Observable<string> {
    return this.http.request(new Request({
      method: RequestMethod.Post,
      url: AUTH_URL + 'forgotpassword',
      body: {email}
    })).map(response => {
      const r = response.json();
      return r.msg;
    });
  }

  resetPassword(password: string, token: string): Observable<any> {
    const request = new Request({
      method: RequestMethod.Post,
      url: AUTH_URL + 'resetpassword',
      body: {password}
    });
    request.headers.set('Authorization', `Bearer ${token}`);
    return this.http.request(request).map(response => response.json());
  }

  getModels(model: any): Observable<any> {
    const currentUser = JSON.parse(localStorage.getItem(LS_KEY));
    const token = currentUser.token

    const request = new Request({
      method: RequestMethod.Post,
      url: API_URL + model,
      body: {creadopor: currentUser.email}
    });
    // console.log(request);
    request.headers.set('Authorization', `Bearer ${token}`);
    return this.http.request(request).map(response => {
      const r = response.json();
      // console.log(r.data);
      return r.data;
    });
  }

  getModelsByProvider(model: any): Observable<any> {
    const currentUser = JSON.parse(localStorage.getItem(LS_KEY));
    const token = currentUser.token
    const ruc = currentUser.codigo

    const request = new Request({
      method: RequestMethod.Post,
      url: API_URL + model,
      body: {idProvider: ruc}
    });

    request.headers.set('Authorization', `Bearer ${token}`);
    return this.http.request(request).map(response => {
      const r = response.json();
      //console.log(r.data);
      return r.data;
    });
  }


  getModel(model: any, _id: string): Observable<any> {
    return this.sendRequest(RequestMethod.Get, `${model}/${_id}`, null, true);
  }

  deleteModel(model: any, _id: string): Observable<any> {
    return this.sendRequest(RequestMethod.Delete, `${model}/${_id}`, null, true);
  }

  saveModel(model: any, obj: any): Observable<any> {
    return this.sendRequest(RequestMethod.Post, `${model}`, obj, true);
  }

  updateModel(model: any, obj: any): Observable<any> {
    return this.sendRequest(RequestMethod.Put, `${model}/${obj._id}`, obj, true);
  }

  getAmount(model: any): Observable<any> {
    const currentUser = JSON.parse(localStorage.getItem(LS_KEY));
    const token = currentUser.token

    const request = new Request({
      method: RequestMethod.Post,
      url: API_URL + model,
      body: {creadopor: currentUser.email}
    });
    request.headers.set('Authorization', `Bearer ${token}`);
    return this.http.request(request).map(response => {
      const r = response.json();
      const amount = r.data.length
      return amount;
    });
  }

  getAmountOfProvider(model: any): Observable<any> {
    const currentUser = JSON.parse(localStorage.getItem(LS_KEY));
    const token = currentUser.token
    const ruc = currentUser.codigo

    const request = new Request({
      method: RequestMethod.Post,
      url: API_URL + model,
      body: {idProvider: ruc}
    });

    request.headers.set('Authorization', `Bearer ${token}`);
    return this.http.request(request).map(response => {
      const r = response.json();
      console.log(r);
      const amount = r.data.length

      return amount;
    });
  }

  getUserInfo(): Observable<any> {
    return this.sendRequest(RequestMethod.Get, 'userInfo', null, true);
  }

  updateUserInfo(param: any): Observable<any> {
    return this.sendRequest(RequestMethod.Post, 'userInfo', param, true);
  }
}

