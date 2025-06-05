import {Component, OnDestroy, OnInit} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {RefreshService} from '../services/refresh.service';
import {Observable, Subscription} from 'rxjs';
import {User} from '../dto/User';
import {Event} from '../dto/Event';
import {FullUser} from '../dto/FullUser';
import {CategoryEvaluationMethod} from '../dto/CategoryEvaluationMethod';
import {environment} from '../../environments/environment';
import {UserService} from '../services/user.service';
import {MenuService} from '../services/menu.service';
import {EventService} from '../services/event.service';

@Component({
  selector: 'app-history-of-events',
  templateUrl: './history-of-events.component.html',
  styleUrls: ['./history-of-events.component.css']
})
export class HistoryOfEventsComponent implements OnInit, OnDestroy {

  readonly environment = environment;

  user$: Observable<User | null> = this.userService.user$;
  events: Event[];
  users = new Map<string, FullUser>();
  categoryEvaluationMethods = new Map<string, CategoryEvaluationMethod>();
  isMenuOpen = true;

  // 2. Состояние “присоединился/отказался”
  joined = new Set<string>();
  private sub: Subscription = new Subscription();


  constructor(
    private titleService: Title,
    private refreshService: RefreshService,
    private userService: UserService,
    private menuService: MenuService,
    private eventService: EventService
  ) {
  }


  ngOnInit(): void {
    this.titleService.setTitle('История событий');

    this.sub.add(
      this.menuService.open$.subscribe(open => {
        this.isMenuOpen = open;
      })
    );

    this.loadData();

    this.sub.add(
      this.refreshService.refresh$.subscribe(() => this.loadData())
    );
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }


  private loadData(): void {
    this.eventService
      .getHistoryEvents()
      .subscribe({
        next: (result: Event[]) => {
          this.events = result;

          // подгружаем пользователей и методы оценки
          this.events.forEach(event => {
            this.userService
              .getFullUserDataByUuid(event.authorUuid)
              .subscribe({
                next: (user: FullUser) => {
                  this.users.set(event.authorUuid, user);
                },
                error: err => {
                  console.error(err);
                  this.userService.logoutAction();
                }
              });

            this.eventService
              .getCategoryEvaluationMethodByUuid(event.categoryEvaluationMethodUuid)
              .subscribe({
                next: (cem: CategoryEvaluationMethod) => {
                  this.categoryEvaluationMethods.set(event.categoryEvaluationMethodUuid, cem);
                },
                error: err => {
                  console.error(err);
                  this.userService.logoutAction();
                }
              });
          });
        },
        error: err => {
          console.error(err);
          this.userService.logoutAction();
        }
      });
  }
}
