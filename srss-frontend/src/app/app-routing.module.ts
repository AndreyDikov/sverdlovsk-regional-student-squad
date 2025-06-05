import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import {SquadsComponent} from './squads/squads.component';
import {HistoryOfEventsComponent} from './history-of-events/history-of-events.component';
import {MyProfileComponent} from './my-profile/my-profile.component';
import {MySquadComponent} from './my-squad/my-squad.component';
import {UsersComponent} from './users/users.component';
import {NotificationsComponent} from './notifications/notifications.component';
import {OtherProfileComponent} from './other-profile/other-profile.component';
import {EventComponent} from './event/event.component';
import {SettingsOfWeightsComponent} from './settings-of-weights/settings-of-weights.component';
import {NewEventComponent} from './new-event/new-event.component';


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'main', component: MainComponent},
  {path: 'squads', component: SquadsComponent},
  {path: 'history-of-events', component: HistoryOfEventsComponent},
  {path: 'my-profile', component: MyProfileComponent},
  {path: 'my-squad', component: MySquadComponent},
  {path: 'users', component: UsersComponent},
  {path: 'notifications', component: NotificationsComponent},
  {path: 'other-profile/:uuid', component: OtherProfileComponent},
  {path: 'event/:uuid', component: EventComponent},
  {path: 'settings-of-weights', component: SettingsOfWeightsComponent},
  {path: 'new-event', component: NewEventComponent}
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
