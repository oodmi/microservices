import { Injectable } from '@angular/core';
import { Friend } from '../shared/model/friend';

@Injectable()
export class DataService {

  constructor() { }

  getRequests(): any {
    return [
      { date: '2018-12-13T11:30:46.965Z', amount: 56, uuid: '12312423423534' },
      { date: '2018-12-13T11:30:46.965Z', amount: 56, uuid: '789789789789' },
      { date: '2018-12-13T11:30:46.965Z', amount: 56, uuid: 'adwdacvawervewrvger' },
      { date: '2018-12-13T11:30:46.965Z', amount: 56, uuid: 'qqqqqqqqqqqqqqqqqqqq' }
    ];
  }

  getByUUID(uuid: number): any {
    return [
      new Friend(1, 'Mark', 'Otto', 'o@O.ru', '', '', '2018-12-13T11:30:46.965Z'),
      new Friend(2, 'Jacob', 'Thornton', 'o@O.ru', '', '', '2018-12-17T11:30:46.965Z'),
      new Friend(3, 'qweqwe', 'qweqweqwe', 'o@O.ru', '', '', '2018-12-11T11:30:46.965Z'),
      new Friend(1, 'Mark', 'Otto', 'o@O.ru', '', '', '2018-12-13T11:30:46.965Z'),
      new Friend(2, 'Jacob', 'Thornton', 'o@O.ru', '', '', '2018-12-17T11:30:46.965Z'),
      new Friend(3, 'qweqwe', 'qweqweqwe', 'o@O.ru', '', '', '2018-12-11T11:30:46.965Z')
    ];
  }

  getDifferenceByUUID(uuid1: string, uuid2: string) {
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

  getDifferenceByDate(date1: Date, date2: Date) {
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
}
