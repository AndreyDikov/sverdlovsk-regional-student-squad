import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({providedIn: 'root'})
export class MenuService {
  // Инициализируем из localStorage, либо true по умолчанию
  private openSubject = new BehaviorSubject<boolean>(
    localStorage.getItem('isMenuOpen') === 'false' ? false : true
  );
  open$ = this.openSubject.asObservable();

  toggle(): void {
    const next = !this.openSubject.value;
    this.openSubject.next(next);
    localStorage.setItem('isMenuOpen', JSON.stringify(next));
  }

  // Если нужно принудительно установить
  set(open: boolean): void {
    this.openSubject.next(open);
    localStorage.setItem('isMenuOpen', JSON.stringify(open));
  }
}
