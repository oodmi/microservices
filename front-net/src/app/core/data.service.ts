import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class DataService {

  httpOptions: any;
  api = 'http://microservices.eastus.cloudapp.azure.com:4000';

  constructor(
    private http: HttpClient,
  ) { }

  setToken() {
    if (!this.httpOptions) {
      let token = localStorage.getItem('token');
      console.log(token);
      if (!token) {
        localStorage.setItem('token', document.cookie.replace(/(?:(?:^|.*;s*)token*=s*([^;]*).*$)|^.*$/, '$1'));
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

  getChartData() : any {
    this.setToken();
    return this.http.get(`${this.api}/information/statistic/`, this.httpOptions).toPromise();
  }

}
