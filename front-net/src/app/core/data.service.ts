import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class DataService {

  httpOptions: any;

  constructor(
    private http: HttpClient,
  ) {}

  getChartData() {
      return [];
  }
}
