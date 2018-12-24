import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DifferenceStateService {

  public uuid1 = '';
  public uuid2 = '';

  constructor() { }

  setUUIDS(uuid1: string, uuid2: string) {
    this.uuid1 = uuid1;
    this.uuid2 = uuid2;
  }

  clearUUIDS() {
    this.uuid1 = '';
    this.uuid2 = '';
  }
}
