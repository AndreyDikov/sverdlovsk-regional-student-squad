import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  visibility: BehaviorSubject<boolean> = new BehaviorSubject(false);


  show(): void {
    this.visibility.next(true);
  }


  hide(): void {
    this.visibility.next(false);
  }
}
