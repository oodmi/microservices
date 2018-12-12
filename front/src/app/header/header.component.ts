import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentPage: string = 'home';

  constructor() { }

  ngOnInit() {
  }

  setPage(state){
    this.currentPage = state;
  }

}
