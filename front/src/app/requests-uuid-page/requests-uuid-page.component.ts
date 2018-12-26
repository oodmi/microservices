import { Component, OnInit } from '@angular/core';
import { DataService } from '../core/data.service';
import { Friend } from '../shared/model/friend';
import { DifferenceStateService } from '../core/difference-state.service';
import * as moment from 'moment';

@Component({
  selector: 'app-requests-uuid-page',
  templateUrl: './requests-uuid-page.component.html',
  styleUrls: ['./requests-uuid-page.component.scss']
})
export class RequestsUuidPageComponent implements OnInit {

  uuid1 = '';
  uuid2 = '';

  newFriends: Array<Friend>;
  removedFriends: Array<Friend>;

  filters = {
    dateFrom: null,
    dateTo: null
  };

  constructor(
    private dataService: DataService,
    private differenceState: DifferenceStateService
  ) { }

  ngOnInit() {
    if (this.differenceState.uuid1 && this.differenceState.uuid1) {
      this.uuid1 = this.differenceState.uuid1;
      this.uuid2 = this.differenceState.uuid2;
      this.getDifference();
      this.differenceState.clearUUIDS();
    }
  }

  async getDifference() {
    try {
      const requests = await this.dataService.getDifferenceByUUID(this.uuid1, this.uuid2);
      this.newFriends = requests.NEW;
      this.removedFriends = requests.REMOVED;
      this.newFriends.forEach(friend => {
        friend.time = moment(friend.time).format('DD MMM YYYY');
        return friend;
      });
      this.removedFriends.forEach(friend => {
        friend.time = moment(friend.time).format('DD MMM YYYY');
        return friend;
      });
    } catch (err) {
      console.log(err);
    }
  }
}
