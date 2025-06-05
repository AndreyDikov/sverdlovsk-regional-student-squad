import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {Observable, Subscription} from 'rxjs';
import {RefreshService} from '../services/refresh.service';
import {User} from '../dto/User';
import {UserService} from '../services/user.service';
import {AdditionalUserData} from '../dto/AdditionalUserData';
import {Squad} from '../dto/Squad';
import {Event} from '../dto/Event';
import {FullUser} from '../dto/FullUser';
import {CategoryEvaluationMethod} from '../dto/CategoryEvaluationMethod';
import {EventService} from '../services/event.service';
import {SquadService} from '../services/squad.service';
import {environment} from '../../environments/environment';
import {hasRole} from '../functions/hasRoleFunction';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  @ViewChild('createdList', {read: ElementRef}) createdList!: ElementRef<HTMLElement>;
  @ViewChild('eventsList', {read: ElementRef}) eventsList!: ElementRef<HTMLElement>;
  @ViewChild('historyList', {read: ElementRef}) historyList!: ElementRef<HTMLElement>;
  // Смещаем на ширину одной карточки + gap
  private scrollAmount = 236; // 220px карточка + 16px gap

  readonly environment = environment;
  readonly length = length;

  private sub: Subscription = new Subscription();

  user$: Observable<User | null> = this.userService.user$;
  additionalUserData: AdditionalUserData;
  squad: Squad | null = null;
  userEvents: Event[] = [];
  createdEvents: Event[] = [];
  historyEvents: Event[] = [];
  authors: Map<string, FullUser> = new Map<string, FullUser>();
  historyAuthors: Map<string, FullUser> = new Map<string, FullUser>();
  categoryEvaluationMethods = new Map<string, CategoryEvaluationMethod>();
  showNotification = false;
  score: Observable<number>;


  constructor(
    private refreshService: RefreshService,
    private titleService: Title,
    private userService: UserService,
    private eventService: EventService,
    private squadService: SquadService
  ) {
  }


  ngOnInit(): void {
    this.titleService.setTitle('Мой профиль');

    this.loadData();

    this.sub.add(
      this.refreshService.refresh$.subscribe(() => this.loadData())
    );
  }


  private loadData(): void {
    this.eventService
      .getAllCategoryEvaluationMethods()
      .subscribe((methods: CategoryEvaluationMethod[]) => {
        methods.forEach((cem) => {
          this.categoryEvaluationMethods.set(cem.uuid, cem);
        });
      });

    this.userService
      .getAdditionalUserData()
      .subscribe({
        next: (data: AdditionalUserData) => {
          this.additionalUserData = data;

          this.userService
            .calculateUserScore(data.userUuid)
            .subscribe(result => this.score = result);

          this.eventService
            .getEventsByAuthorUuid(this.additionalUserData.userUuid)
            .subscribe({
              next: (events: Event[]) => {
                this.createdEvents = events;
              },
              error: err => {
                console.error(err);
                this.userService.logoutAction();
              }
            });

          this.squadService
            .getMySquad()
            .subscribe({
              next: (squad: Squad) => {
                this.squad = squad;
              },
              error: err => {
                console.error(err);
              }
            });

          this.eventService
            .getUserEvents(this.additionalUserData.userUuid)
            .subscribe({
              next: (events: Event[]) => {
                this.userEvents = events;

                this.userEvents.forEach(event => {
                  this.userService
                    .getFullUserDataByUuid(event.authorUuid)
                    .subscribe(u => {
                      this.authors.set(event.authorUuid, u);
                    });
                });
              },
              error: err => {
                console.error(err);
                this.userService.logoutAction();
              }
            });

          this.eventService
            .getUserEventsHistory(this.additionalUserData.userUuid)
            .subscribe({
              next: (events: Event[]) => {
                this.historyEvents = events;

                this.historyEvents.forEach(event => {
                  this.userService
                    .getFullUserDataByUuid(event.authorUuid)
                    .subscribe(u => {
                      this.historyAuthors.set(event.authorUuid, u);
                    });
                });
              },
              error: err => {
                console.error(err);
                this.userService.logoutAction();
              }
            });
        },
        error: err => {
          console.error(err);
          this.userService.logoutAction();
        }
      });
  }


  copyText(text: string): void {
    navigator.clipboard.writeText(text).then(() => {
      this.showNotification = true;

      // Убираем уведомление через 2 секунды
      setTimeout(() => {
        this.showNotification = false;
      }, 2000);
    }).catch(err => {
      console.error('Ошибка при копировании текста: ', err);
    });
  }

  scrollCreatedRight(): void {
    this.createdList.nativeElement.scrollBy({left: this.scrollAmount, behavior: 'smooth'});
  }

  scrollCreatedLeft(): void {
    this.createdList.nativeElement.scrollBy({left: -this.scrollAmount, behavior: 'smooth'});
  }

  scrollEventsRight(): void {
    this.eventsList.nativeElement.scrollBy({left: this.scrollAmount, behavior: 'smooth'});
  }

  scrollEventsLeft(): void {
    this.eventsList.nativeElement.scrollBy({left: -this.scrollAmount, behavior: 'smooth'});
  }

  scrollHistoryRight(): void {
    this.historyList.nativeElement.scrollBy({left: this.scrollAmount, behavior: 'smooth'});
  }

  scrollHistoryLeft(): void {
    this.historyList.nativeElement.scrollBy({left: -this.scrollAmount, behavior: 'smooth'});
  }

  protected readonly hasRole = hasRole;
}
