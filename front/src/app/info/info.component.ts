import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { DataService } from '../core/data.service';
import { DifferenceStateService } from '../core/difference-state.service';

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

  ngOnInit() {
    this.requests = this.dataService.getRequests();
  }

  openDifferenceUUID() {
    this.differenceState.setUUIDS(this.selectedRequests[0], this.selectedRequests[1]);
    this.router.navigateByUrl('difference/uuid');
  }

  selectRequest(uuid: number) {
    const index = this.selectedRequests.indexOf(uuid);
    if ( index === -1) {
      this.selectedRequests.push(uuid);
    } else {
      this.selectedRequests.splice(index, 1);
    }
  }

}
