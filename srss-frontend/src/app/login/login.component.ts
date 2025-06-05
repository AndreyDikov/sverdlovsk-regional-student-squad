import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: HttpClient
  ) {
  }


  ngOnInit(): void {
    this.activatedRoute
      .queryParams
      .subscribe(params => {
        if (params.code) {
          const code: any = params.code;
          const state: any = params.state;

          window.history.pushState({}, '', document.location.href.split('?')[0]);

          this.requestTokens(code, state);
          return;
        }

        this.showAuthWindow();
      });
  }


  private showAuthWindow(): void {
    const state = this.randomString(40);
    localStorage.setItem('state', state);

    const params: string[] = [
      'response_type=code',
      'state=' + state,
      'client_id=' + environment.kcClientID,
      'scope=openid',
      'redirect_uri=' + encodeURIComponent(environment.redirectURI),
    ];

    const url = environment.kcBaseURL + '/auth' + '?' + params.join('&');

    window.open(url, '_self');
  }


  private randomString(
    length: number
  ): string {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength: number = characters.length;

    for (let i = 0; i < length; i++) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }

    return result;
  }


  private requestTokens(
    code: string,
    state: string
  ): void {

    if (!this.checkState(state)) {
      return;
    }

    this.http
      .post(environment.bffURI + '/token/all-tokens', code, {
        headers: {
          'Content-Type': 'application/json; charset=UTF-8'
        }
      })
      .subscribe({
        next: ((response: any) => {
          this.router
            .navigate(['main'])
            .then(r => {
            });
        }),

        error: (error => {
          console.log(error);
        })
      });
  }


  private checkState(
    state: string
  ): boolean {
    if (state !== localStorage.getItem('state') as string) {
      console.log('Invalid state');
      return false;
    }

    localStorage.removeItem('state');
    return true;
  }
}
