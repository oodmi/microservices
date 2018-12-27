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

}
