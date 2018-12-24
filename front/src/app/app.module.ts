import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatDatepickerModule,
  MatFormFieldModule,
  MatNativeDateModule,
  MatInputModule,
  MatButtonModule,
  MatCheckboxModule
} from '@angular/material';

import { DataService } from './core/data.service';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InfoComponent } from './info/info.component';
import { RequestsUuidPageComponent } from './requests-uuid-page/requests-uuid-page.component';
import { HeaderComponent } from './header/header.component';
import { RequestsDatePageComponent } from './requests-date-page/requests-date-page.component';
import { UuidPageComponent } from './uuid-page/uuid-page.component';
import { NewRemovedTableComponent } from './shared/components/new-removed-table/new-removed-table.component';
import { TwoSidesTableComponent } from './shared/components/two-sides-table/two-sides-table.component';

@NgModule({
  declarations: [
    AppComponent,
    InfoComponent,
    RequestsUuidPageComponent,
    HeaderComponent,
    RequestsDatePageComponent,
    UuidPageComponent,
    NewRemovedTableComponent,
    TwoSidesTableComponent
  ],
  imports: [
    MDBBootstrapModule.forRoot(),
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatInputModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    FormsModule,
    MatButtonModule,
    MatCheckboxModule
  ],
  exports: [
    MatDatepickerModule,
    MatFormFieldModule
  ],
  providers: [DataService, MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
