import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../core/data.service';
import { Friend } from '../shared/model/friend';

@Component({
  selector: 'app-requests-uuid-page',
  templateUrl: './requests-uuid-page.component.html',
  styleUrls: ['./requests-uuid-page.component.scss']
})
export class RequestsUuidPageComponent implements OnInit {

  newFriends: Array<Friend> = [];
  removedFriends: Array<Friend> = [];

  filters = {
    dateFrom: null,
    dateTo: null
  };

  constructor(
    private route: ActivatedRoute,
    private dataService: DataService
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const requests = this.dataService.getByUUID(params.uuid);
      this.newFriends = requests.NEW;
      this.removedFriends = requests.REMOVED;
    });
  }

  dateFromChange(event: any) {
    this.filters.dateFrom = new Date(event.value);
  }

  dateToChange(event: any) {
    this.filters.dateTo = new Date(event.value);
  }

  viewNewFriends() {
    let view = this.newFriends;
    if (this.filters.dateFrom) {
      view = view.filter(req => new Date(req.time) >= this.filters.dateFrom);
    }

    if (this.filters.dateTo) {
      view = view.filter(req => new Date(req.time) <= this.filters.dateTo);
    }

    return view;
  }

  viewRemovedFriends() {
    let view = this.removedFriends;
    if (this.filters.dateFrom) {
      view = view.filter(req => new Date(req.time) >= this.filters.dateFrom);
    }

    if (this.filters.dateTo) {
      view = view.filter(req => new Date(req.time) <= this.filters.dateTo);
    }

    return view;
  }
}
