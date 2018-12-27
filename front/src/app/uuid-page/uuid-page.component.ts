import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../core/data.service';
import { Friend } from '../shared/model/friend';
import * as moment from 'moment';

@Component({
  selector: 'app-uuid-page',
  templateUrl: './uuid-page.component.html',
  styleUrls: ['./uuid-page.component.scss']
})
export class UuidPageComponent implements OnInit {

  uuid: string;
  friends: Array<Friend> = [];
  headers = ['ID', 'Фото', 'Имя', 'Домен', 'Почта', ''];
  filters = {
    name: null
  };

  constructor(
    private dataService: DataService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.route.params.subscribe(async (params) => {
      this.uuid = params.uuid;
      this.friends = await this.dataService.getByUUID(params.uuid);
      this.friends = this.friends.map(friend => {
        friend.fullName = friend.name + ' ' + friend.surname;
        friend.time = moment(friend.time).format('DD MMM YYYY');
        return friend;
      });
    });
  }

  viewFriends() {
    let view = this.friends;
   if (this.filters.name) {
     view = view.filter(friend => {
       const fullName = friend.fullName.toLowerCase();
       const name = this.filters.name.toLowerCase();
       return fullName.indexOf(name) !== -1;
     });
   }
    return view;
  }

}
