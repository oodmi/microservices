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
  logout() {
    this.setCookie('token', '', {
      expires: -1
    });
    localStorage.removeItem('token');
    window.location.href = 'http://microservices.eastus.cloudapp.azure.com:4000/';
  }

  setCookie(name, value, options) {
    options = options || {};

    let expires = options.expires;

    if (typeof expires === 'number' && expires) {
      const d = new Date();
      d.setTime(d.getTime() + expires * 1000);
      expires = options.expires = d;
    }
    if (expires && expires.toUTCString) {
      options.expires = expires.toUTCString();
    }

    value = encodeURIComponent(value);

    let updatedCookie = name + '=' + value;

    for (const propName of Object.keys(options)) {
      updatedCookie += '; ' + propName;
      const propValue = options[propName];
      if (propValue !== true) {
        updatedCookie += '=' + propValue;
      }
    }

    document.cookie = updatedCookie;
  }

}
