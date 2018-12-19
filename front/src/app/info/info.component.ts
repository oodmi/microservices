import { Component, OnInit } from '@angular/core';
import { DataService } from '../core/data.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss']
})
export class InfoComponent implements OnInit {

  requests = [];
  headers = ['Время', 'Идентификатор', 'Количество', ''];
  constructor(
    private dataService: DataService
  ) { }

  ngOnInit() {
    this.requests = this.dataService.getRequests();
  }

}
