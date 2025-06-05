import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {SpinnerService} from '../../services/spinner.service';


@Injectable()
export class SpinnerInterceptor implements HttpInterceptor {

  constructor(
    private spinnerService: SpinnerService
  ) {
  }


  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    this.spinnerService.show();

    return next.handle(request)
      .pipe(
        tap({
          next: (event: HttpEvent<any>): void => {
            if (event instanceof HttpResponse) {
              this.spinnerService.hide();
            }
          },

          error: (error: any): void => {
            console.log(error);
            this.spinnerService.hide();
          }
        })
      );
  }
}
