import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MainComponent} from './main/main.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CookiesInterceptor} from './login/interceptors/cookies-interceptor.service';
import {SpinnerInterceptor} from './login/interceptors/spinner-interceptor.service';
import {NgOptimizedImage} from '@angular/common';
import { SquadsComponent } from './squads/squads.component';
import { HistoryOfEventsComponent } from './history-of-events/history-of-events.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { MySquadComponent } from './my-squad/my-squad.component';
import { UsersComponent } from './users/users.component';
import {SharedModule} from './shared/shared.module';
import {MatTooltipModule} from '@angular/material/tooltip';
import { NotificationsComponent } from './notifications/notifications.component';
import { OtherProfileComponent } from './other-profile/other-profile.component';
import { EventComponent } from './event/event.component';
import { SettingsOfWeightsComponent } from './settings-of-weights/settings-of-weights.component';
import { NewEventComponent } from './new-event/new-event.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainComponent,
    SquadsComponent,
    HistoryOfEventsComponent,
    MyProfileComponent,
    MySquadComponent,
    UsersComponent,
    NotificationsComponent,
    OtherProfileComponent,
    EventComponent,
    SettingsOfWeightsComponent,
    NewEventComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgOptimizedImage,
    SharedModule,
    MatTooltipModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CookiesInterceptor,
      multi: true
    },

    {
      provide: HTTP_INTERCEPTORS,
      useClass: SpinnerInterceptor,
      multi: true
    },
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
