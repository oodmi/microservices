import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-two-sides-table',
  templateUrl: './two-sides-table.component.html',
  styleUrls: ['./two-sides-table.component.scss']
})
export class TwoSidesTableComponent implements OnInit {

  @Input() leftData = [];
  @Input() rightData = [];

  constructor() { }

  ngOnInit() {
  }

}
