import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {User} from '../dto/User';


@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  constructor(
    private http: HttpClient
  ) {
  }


  logoutAction(): Observable<any> {
    return this.http.get(environment.bffURI + '/profile/logout');
  }


  exchangeRefreshToken(): Observable<any> {
    return this.http.get(environment.bffURI + '/token/exchange');
  }


  requestUserProfile(): Observable<User> {
    return this.http.get<User>(environment.bffURI + '/profile/read');
  }
}
