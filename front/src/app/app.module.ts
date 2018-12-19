import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule, MatFormFieldModule, MatNativeDateModule, MatInputModule } from '@angular/material';

import { DataService } from './core/data.service';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InfoComponent } from './info/info.component';
import { RequestsUuidPageComponent } from './requests-uuid-page/requests-uuid-page.component';
import { HeaderComponent } from './header/header.component';
import { HistoryPageComponent } from './history-page/history-page.component';
import { FriendPageComponent } from './friend-page/friend-page.component';

@NgModule({
  declarations: [
    AppComponent,
    InfoComponent,
    RequestsUuidPageComponent,
    HeaderComponent,
    HistoryPageComponent,
    FriendPageComponent
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
    FormsModule
  ],
  exports: [
    MatDatepickerModule,
    MatFormFieldModule
  ],
  providers: [DataService, MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
