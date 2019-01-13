import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../core/data.service';
import { DifferenceStateService } from '../core/difference-state.service';
import * as moment from 'moment';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss']
})
export class InfoComponent implements OnInit {

  firstIndex = 1;
  lastIndex = 1;
  pagesCount;
  currentPage;
  totalItems;
  itemsPerPage = 5;

  requests = [];

  selectedRequests = [];
  maxSelected = 2;

  headers = ['Время', 'Идентификатор', 'Количество', '', 'Выбрать'];
  constructor(
    private dataService: DataService,
    private differenceState: DifferenceStateService,
    private router: Router
  ) { }

  async ngOnInit() {
    this.requests = await this.getRequests();
    this.totalItems = this.requests.length;
    this.pagesCount = Math.ceil(this.totalItems / this.itemsPerPage);
    this.currentPage = 0;
    this.firstIndex = 1;
    this.lastIndex = this.itemsPerPage;
  }

  async getRequests() {
    try {
      let requests = await this.dataService.getRequests();
      requests = requests.sort((a, b) => {
        const da = new Date(a);
        const db = new Date(b);
        if (da > db) {
          return 1;
        } else {
          return -1;
        }
      });
      requests = requests.map(req => {
        req.time = moment(req.time).format('DD MMM YYYY');
        req.uuid = req.uuid.replace('"', '');
        req.uuid = req.uuid.replace('"', '');
        return req;
      });
      return requests;
    } catch (err) {
      console.log(err);
    }
  }

  openDifferenceUUID() {
    this.differenceState.setUUIDS(this.selectedRequests[0], this.selectedRequests[1]);
    this.router.navigateByUrl('difference/uuid');
  }

  selectRequest(uuid: number) {
    const index = this.selectedRequests.indexOf(uuid);
    if (index === -1) {
      this.selectedRequests.push(uuid);
    } else {
      this.selectedRequests.splice(index, 1);
    }
  }

  async update() {
    try {
      await this.dataService.updateStatistics();
    } catch (error) {
      console.log(error);
    } finally {
      this.requests = await this.getRequests();
    }
  }

  viewRequests() {
    let view = this.requests;
    if (this.totalItems !== view.length) {
      this.currentPage = 0;
      this.totalItems = view.length;
      this.pagesCount = Math.ceil(this.totalItems / this.itemsPerPage);
      this.firstIndex = 1;
      this.lastIndex = this.itemsPerPage;
    }

    view = view.slice(this.currentPage * this.itemsPerPage, this.currentPage * this.itemsPerPage + this.itemsPerPage);
    return view;
  }


  nextPage() {
    if (this.currentPage < this.pagesCount - 1) {
      this.currentPage++;
      this.firstIndex = this.currentPage * this.itemsPerPage + 1;
      this.lastIndex = this.currentPage * this.itemsPerPage + this.itemsPerPage;
      if (this.lastIndex > this.totalItems){
        this.lastIndex = this.totalItems;
      }
    }
  }

  prevPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.firstIndex = this.currentPage * this.itemsPerPage + 1;
      this.lastIndex = this.currentPage * this.itemsPerPage + this.itemsPerPage;
    }
  }

}
