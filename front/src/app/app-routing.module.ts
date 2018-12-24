import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InfoComponent } from './info/info.component';
import { RequestsUuidPageComponent } from './requests-uuid-page/requests-uuid-page.component';
import { RequestsDatePageComponent } from './requests-date-page/requests-date-page.component';
import { UuidPageComponent } from './uuid-page/uuid-page.component';

const routes: Routes = [
  { path: '', component: InfoComponent },
  { path: 'uuid/:uuid', component: UuidPageComponent },
  { path: 'difference/uuid', component: RequestsUuidPageComponent},
  { path: 'difference/date', component: RequestsDatePageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
