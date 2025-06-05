import {Component, OnDestroy, OnInit} from '@angular/core';
import {SpinnerService} from '../../services/spinner.service';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {RefreshService} from '../../services/refresh.service';
import {MenuService} from '../../services/menu.service';
import {Subscription} from 'rxjs';
import {hasRole} from '../../functions/hasRoleFunction';

@Component({
  selector: 'app-snippet',
  templateUrl: './snippet.component.html',
  styleUrls: ['./snippet.component.css']
})
export class SnippetComponent implements OnInit, OnDestroy {
  spinner: SpinnerService;
  user$ = this.userService.user$;
  protected readonly hasRole = hasRole;

  // Будем держать локальную копию для шаблона
  isMenuOpen = true;
  private sub = new Subscription();

  constructor(
    private spinnerService: SpinnerService,
    private userService: UserService,
    private refreshService: RefreshService,
    private menuService: MenuService
  ) {
    this.spinner = this.spinnerService;
  }

  ngOnInit(): void {
    // Подписываемся на текущее и будущие значения
    this.sub.add(
      this.menuService.open$.subscribe(open => {
        this.isMenuOpen = open;
      })
    );
    // Логин-логи
    this.user$.subscribe(u => console.log('User in Snippet:', u));
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  /** Вызывать при клике по «бургеру» */
  toggleMenu(): void {
    this.menuService.toggle();
  }

  logout(): void {
    this.userService.logoutAction();
  }

  onLogoClick(): void {
    this.refreshService.trigger();
  }
}
