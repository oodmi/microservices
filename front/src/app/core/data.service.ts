import { Injectable } from '@angular/core';
import { Friend } from '../shared/model/friend';

@Injectable()
export class DataService {

  constructor() { }

  getFullData() {
    return [
      new Friend(1, 'Mark', 'Otto', 'o@O.ru', '', '', '2018-12-13T11:30:46.965Z'),
      new Friend(2, 'Jacob', 'Thornton', 'o@O.ru', '', '', '2018-12-17T11:30:46.965Z'),
      new Friend(3, 'qweqwe', 'qweqweqwe', 'o@O.ru', '', '', '2018-12-11T11:30:46.965Z'),
      new Friend(1, 'Mark', 'Otto', 'o@O.ru', '', '', '2018-12-13T11:30:46.965Z'),
      new Friend(2, 'Jacob', 'Thornton', 'o@O.ru', '', '', '2018-12-17T11:30:46.965Z'),
      new Friend(3, 'qweqwe', 'qweqweqwe', 'o@O.ru', '', '', '2018-12-11T11:30:46.965Z')
    ];
  }

  getRequests(): any {
    return [
      {date : '2018-12-13T11:30:46.965Z', amount: 56, uuid: 'oikergioergjoiergiojgeroi' },
      {date : '2018-12-13T11:30:46.965Z', amount: 56, uuid: 'oikergioergjoiergiojgeroi' },
      {date : '2018-12-13T11:30:46.965Z', amount: 56, uuid: 'oikergioergjoiergiojgeroi' },
      {date : '2018-12-13T11:30:46.965Z', amount: 56, uuid: 'oikergioergjoiergiojgeroi' }
    ];
  }

  getByUUID(uuid: number): any {
    return {
      REMOVED: [
        new Friend(1, 'Mark', 'Otto', 'o@O.ru', '', '', '2018-12-13T11:30:46.965Z'),
        new Friend(2, 'Jacob', 'Thornton', 'o@O.ru', '', '', '2018-12-17T11:30:46.965Z'),
        new Friend(3, 'qweqwe', 'qweqweqwe', 'o@O.ru', '', '', '2018-12-11T11:30:46.965Z'),
      ],
      NEW: [
        new Friend(5, 'zxczxc', 'Otto', 'o@O.ru', '', '', '2018-12-13T11:30:46.965Z'),
        new Friend(6, 'cccccccc', 'Thornton', 'o@O.ru', '', '', '2018-11-17T11:30:46.965Z'),
        new Friend(7, 'ssssssss', 'qweqweqwe', 'o@O.ru', '', '', '2018-10-11T11:30:46.965Z'),
      ]
    };
  }

  getFriend() {

  }
}
