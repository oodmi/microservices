import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InfoComponent } from './info/info.component';
import { RequestsUuidPageComponent } from './requests-uuid-page/requests-uuid-page.component';
import { HistoryPageComponent } from './history-page/history-page.component';
import { FriendPageComponent } from './friend-page/friend-page.component';

const routes: Routes = [
  { path: '', component: InfoComponent },
  { path: 'history', component: HistoryPageComponent },
  { path: 'friend/:id', component: FriendPageComponent },
  { path: 'uuid/:uuid', component: RequestsUuidPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
