import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InfoComponent } from './info/info.component';
import { RequestsDatePageComponent } from './requests-date-page/requests-date-page.component';
import { RequestsUuidPageComponent } from './requests-uuid-page/requests-uuid-page.component';
import { RequestsTableComponent } from './shared/components/requests-table/requests-table.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    InfoComponent,
    RequestsDatePageComponent,
    RequestsUuidPageComponent,
    RequestsTableComponent,
    HeaderComponent
  ],
  imports: [
    MDBBootstrapModule.forRoot(),
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
