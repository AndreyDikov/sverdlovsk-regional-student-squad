import {Component, OnDestroy, OnInit} from '@angular/core';
import {EventService} from '../services/event.service';
import {Event} from '../dto/Event';
import {Title} from '@angular/platform-browser';
import {UserService} from '../services/user.service';
import {Observable, Subscription} from 'rxjs';
import {RefreshService} from '../services/refresh.service';
import {FullUser} from '../dto/FullUser';
import {CategoryEvaluationMethod} from '../dto/CategoryEvaluationMethod';
import {MenuService} from '../services/menu.service';
import {User} from '../dto/User';
import {environment} from '../../environments/environment';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit, OnDestroy {

  readonly environment = environment;

  user$: Observable<User | null> = this.userService.user$;
  events: Event[];
  users = new Map<string, FullUser>();
  categoryEvaluationMethods = new Map<string, CategoryEvaluationMethod>();
  isMenuOpen = true;

  // 2. Состояние “присоединился/отказался”
  joined = new Set<string>();
  private sub = new Subscription();


  constructor(
    private userService: UserService,
    private eventService: EventService,
    private titleService: Title,
    private refreshService: RefreshService,
    private menuService: MenuService
  ) {
  }


  ngOnInit(): void {
    this.titleService.setTitle('Главная');
    this.user$.subscribe(u => console.log('User in Squads:', u));
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


  /** Переключение “+”/“−” */
  toggleJoin(ev: Event): void {
    if (this.joined.has(ev.uuid)) {
      this.joined.delete(ev.uuid);
    } else {
      this.joined.add(ev.uuid);
    }
  }


  /** Загрузка данных */
  private loadData(): void {
    this.eventService.getAllEvents().subscribe({
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
