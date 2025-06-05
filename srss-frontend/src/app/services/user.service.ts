import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {User} from '../dto/User';
import {tap} from 'rxjs/operators';
import {KeycloakService} from './keycloak.service';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {HttpMethod} from '../enums/HttpMethod';
import {buildQueryString} from '../functions/buildQueryStringFunction';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<User | null>(null);

  public user$: Observable<User | null> = this.userSubject.asObservable();


  constructor(
    private http: HttpClient,
    private keycloakService: KeycloakService,
    private router: Router
  ) {
    this.loadUser();
  }


  private loadUser(): void {
    this.keycloakService
      .requestUserProfile()
      .pipe(
        tap({
          next: (profile: User) => this.userSubject.next(profile),
          error: err => {
            console.error('Ошибка получения профиля:', err);
            // при необходимости пробуем обновить токен
            this.exchangeRefreshToken();
          }
        })
      )
      .subscribe();
  }


  exchangeRefreshToken(): void {
    this.keycloakService
      .exchangeRefreshToken()
      .subscribe({
        next: () => {
          this.loadUser();                // после успешного рефреша – заново грузим пользователя
        },
        error: err => {
          console.error('Не удалось обновить токен:', err);
          this.router
            .navigate(['/login'])
            .then();  // перенаправляем на страницу логина
        }
      });
  }


  logoutAction(): void {
    this.keycloakService
      .logoutAction()
      .subscribe({
        complete: (() => {
          this.router
            .navigate(['/login'])
            .then();
        }),
      });
  }


  getAdditionalUserData(): Observable<any> {
    return this.http.post(environment.bffURI + '/operation', {
      url: environment.resourceServerURL + environment.userMicroserviceURL + '/additional-user-data/read',
      method: HttpMethod.GET
    });
  }


  getFullUserDataByUuid(uuid: string): Observable<any> {
    const params: string = buildQueryString(new Map<string, any>([
      ['user-uuid', uuid]
    ]));

    return this.http.post(environment.bffURI + '/operation', {
      url: environment.resourceServerURL + environment.userMicroserviceURL + '/user/read' + params,
      method: HttpMethod.GET
    });
  }


  getAllUsers(): Observable<any> {
    return this.http.post(environment.bffURI + '/operation', {
      url: environment.resourceServerURL + environment.userMicroserviceURL + '/user/read-all',
      method: HttpMethod.GET
    });
  }


  getUsersBySquadUuid(uuid: string): Observable<any> {
    const params: string = buildQueryString(new Map<string, any>([
      ['squad-uuid', uuid]
    ]));

    return this.http.post(environment.bffURI + '/operation', {
      url: environment.resourceServerURL + environment.userMicroserviceURL + '/user/read-by-squad-uuid' + params,
      method: HttpMethod.GET
    });
  }


  calculateUserScore(userUuid: string): Observable<any> {
    const params: string = buildQueryString(new Map<string, any>([
      ['user-uuid', userUuid]
    ]));

    return this.http.post(environment.bffURI + '/operation', {
      url: environment.resourceServerURL
        + environment.eventMicroserviceURL
        + '/user-event/calculate-user-score' + params,
      method: HttpMethod.GET
    });
  }
}
