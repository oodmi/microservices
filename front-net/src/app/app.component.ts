import { Component, OnInit } from '@angular/core';
import { DataService } from './core/data.service';
import * as moment from 'moment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})


export class AppComponent implements OnInit {

  constructor(
    private dataService : DataService
  ){}

  async ngOnInit(){
    this.dataService.setToken();
    const chartData = await this.dataService.getChartData();
    const chartLabels = chartData.map(d => moment(d.date).format('DD MMM hh:mm:ss'));
    const chartDataset = {
      data : chartData.map(d => d.amount),
      label : 'Мои друзья'
    }
    this.chartDatasets.push(chartDataset);
    this.chartLabels = chartLabels;
  }
  public chartType = 'line';

  public chartDatasets: Array<any> = [];

  public chartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgba(105, 0, 132, .2)',
      borderColor: 'rgba(200, 99, 132, .7)',
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };
  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }
}
