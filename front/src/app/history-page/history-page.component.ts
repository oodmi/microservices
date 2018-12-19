import { Component, OnInit } from '@angular/core';
import { DataService } from '../core/data.service';
import { Friend } from '../shared/model/friend';

@Component({
  selector: 'app-history-page',
  templateUrl: './history-page.component.html',
  styleUrls: ['./history-page.component.scss']
})
export class HistoryPageComponent implements OnInit {

  friends: Array<Friend> = [];
  headers = ['id', 'Photo', 'Name', 'Surname', 'Email', 'Time'];
  filters = {
    uuid: '',
    dateFrom: null,
    dateTo: null
  };

  constructor(
    private dataService: DataService
  ) { }

  ngOnInit() {
    this.friends = this.dataService.getFullData();
  }

  dateFromChange(event: any) {
    this.filters.dateFrom = new Date(event.value);
  }

  dateToChange(event: any) {
    this.filters.dateTo = new Date(event.value);
  }

  viewFriends() {
    let view = this.friends;
    if (this.filters.uuid) {
      view = this.friends.filter(friend => friend.uuid && friend.uuid.indexOf(this.filters.uuid) !== 0);
    }
    if (this.filters.dateFrom) {
      view = view.filter(friend => new Date(friend.time) >= this.filters.dateFrom);
    }
    if (this.filters.dateTo) {
      view = view.filter(friend => new Date(friend.time) <= this.filters.dateTo);
    }
    return view;
  }

}
