import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../core/data.service';
import { Friend } from '../shared/model/friend';

@Component({
  selector: 'app-uuid-page',
  templateUrl: './uuid-page.component.html',
  styleUrls: ['./uuid-page.component.scss']
})
export class UuidPageComponent implements OnInit {


  friends: Array<Friend> = [];
  headers = ['id', 'Photo', 'Name', 'Surname', 'Email', 'Time'];
  filters = {
    dateFrom: null,
    dateTo: null
  };

  constructor(
    private dataService: DataService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.friends = this.dataService.getByUUID(params.uuid);
    });
  }

  dateFromChange(event: any) {
    this.filters.dateFrom = new Date(event.value);
  }

  dateToChange(event: any) {
    this.filters.dateTo = new Date(event.value);
  }

  viewFriends() {
    let view = this.friends;
    if (this.filters.dateFrom) {
      view = view.filter(friend => new Date(friend.time) >= this.filters.dateFrom);
    }
    if (this.filters.dateTo) {
      view = view.filter(friend => new Date(friend.time) <= this.filters.dateTo);
    }
    return view;
  }

}
