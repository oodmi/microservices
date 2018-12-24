import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart, Event } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentPage = '/';

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    if (window.location.pathname !== this.currentPage) {
      this.currentPage = window.location.pathname;
    }
    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationStart) {
          this.currentPage = event.url;
      }
  });
  }

}
