import { Component, OnInit } from '@angular/core';
import { DataService } from '../core/data.service';
import { Friend } from '../shared/model/friend';

@Component({
  selector: 'app-requests-date-page',
  templateUrl: './requests-date-page.component.html',
  styleUrls: ['./requests-date-page.component.scss']
})
export class RequestsDatePageComponent implements OnInit {

  dateFrom: Date;
  dateTo: Date;

  newFriends: Array<Friend>;
  removedFriends: Array<Friend>;

  constructor(
    private dataService: DataService
  ) { }

  ngOnInit() {
  }

  getDifference() {
    const requests = this.dataService.getDifferenceByDate(this.dateFrom, this.dateTo);
    this.newFriends = requests.NEW;
    this.removedFriends = requests.REMOVED;
  }

  dateFromChange(event: any) {
    this.dateFrom = new Date(event.value);
  }

  dateToChange(event: any) {
    this.dateTo = new Date(event.value);
  }
}
