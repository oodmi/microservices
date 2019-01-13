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

  firstIndex = 1;
  lastIndex = 1;
  pagesCount;
  currentPage;
  totalItems;
  itemsPerPage = 5;

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
      this.totalItems = this.friends.length;
      this.pagesCount = Math.ceil(this.totalItems / this.itemsPerPage);
      this.currentPage = 0;
      this.firstIndex = 1;
      this.lastIndex = this.itemsPerPage;
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
