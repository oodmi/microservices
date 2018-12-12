import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InfoComponent } from './info/info.component';
import { RequestsDatePageComponent } from './requests-date-page/requests-date-page.component';
import { RequestsUuidPageComponent } from './requests-uuid-page/requests-uuid-page.component';

const routes: Routes = [
  { path: '', component: InfoComponent },
  { path: 'date', component: RequestsDatePageComponent },
  { path: 'uuid', component: RequestsUuidPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
