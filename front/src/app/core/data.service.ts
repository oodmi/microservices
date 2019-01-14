import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Friend } from '../shared/model/friend';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import * as moment from 'moment';

@Injectable()
export class DataService {

  api = environment.api;

  httpOptions: any;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
   }

   setToken() {
     if (!this.httpOptions) {
      let token = localStorage.getItem('token');
      if (!token) {
        localStorage.setItem('token', document.cookie.replace(/(?:(?:^|.*;s*)token*=s*([^;]*).*$)|^.*$/, '$1') );
        token = localStorage.getItem('token');
      }
      if (!token) {
        window.location.href = 'http://microservices.eastus.cloudapp.azure.com:4000';
      } else {
        this.httpOptions = {
          headers: new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            'Authorization': token
          })
        };
      }
     }

   }

   clearToken() {
     localStorage.removeItem('token');
     window.location.href = 'http://microservices.eastus.cloudapp.azure.com:4000';
   }

  getRequests(): any {
    this.setToken();
    return this.http.get(`${this.api}/information/friend/`, this.httpOptions).toPromise();
  }

  getByUUID(uuid: number): any {
    this.setToken();
    return this.http.get(`${this.api}/information/friend/uuid/${uuid}`, this.httpOptions).toPromise();
  }

  getDifferenceByUUID(uuid1: string, uuid2: string): any {
    this.setToken();
    return this.http.get(`${this.api}/information/friend/difference/uuid?` +
    `first=${uuid1}&second=${uuid2}`, this.httpOptions).toPromise();
  }

  getDifferenceByDate(date1: Date, date2: Date): any {
    const dateFrom = moment(date1).format('DD.MM.YYYY+HH:mm:ss');
    const dateTo = moment(date2).add(1, 'days').format('DD.MM.YYYY+HH:mm:ss');
    this.setToken();

    return this.http.get(`${this.api}/information/friend/difference/time?` +
    `from=${dateFrom}&to=${dateTo}`, this.httpOptions).toPromise();
  }

  updateStatistics() {
    this.setToken();
    return this.http.get(`${this.api}/information/friend/update/`, this.httpOptions).toPromise();
  }
}
