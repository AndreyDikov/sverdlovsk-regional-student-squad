import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {UserService} from '../services/user.service';
import {FullUser} from '../dto/FullUser';
import {Title} from '@angular/platform-browser';
import {EventService} from '../services/event.service';
import {Event} from '../dto/Event';
import {environment} from '../../environments/environment';
import {CategoryEvaluationMethod} from '../dto/CategoryEvaluationMethod';
import {Squad} from '../dto/Squad';
import {SquadService} from '../services/squad.service';
import e from 'cors';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-other-profile',
  templateUrl: './other-profile.component.html',
  styleUrls: ['./other-profile.component.css']
})
export class OtherProfileComponent implements OnInit {

  @ViewChild('createdList', {read: ElementRef}) createdList!: ElementRef<HTMLElement>;
  @ViewChild('eventsList', {read: ElementRef}) eventsList!: ElementRef<HTMLElement>;
  @ViewChild('historyList', {read: ElementRef}) historyList!: ElementRef<HTMLElement>;
  // Смещаем на ширину одной карточки + gap
  private scrollAmount = 236; // 220px карточка + 16px gap

  readonly environment = environment;
  readonly length = length;

  user: FullUser | null = null;
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
    private route: ActivatedRoute,
    private userService: UserService,
    private titleService: Title,
    private eventService: EventService,
    private squadService: SquadService
  ) {
  }


  ngOnInit(): void {
    this.loadData();
  }


  private loadData(): void {
    this.eventService
      .getAllCategoryEvaluationMethods()
      .subscribe((methods: CategoryEvaluationMethod[]) => {
        methods.forEach((cem) => {
          this.categoryEvaluationMethods.set(cem.uuid, cem);
        });
      });

    this.route
      .paramMap
      .subscribe((params: ParamMap) => {
        const uuid = params.get('uuid');
        if (uuid) {
          this.userService
            .calculateUserScore(uuid)
            .subscribe(result => this.score = result);

          this.userService
            .getFullUserDataByUuid(uuid)
            .subscribe(u => {
              this.user = u;
              this.titleService.setTitle(u.name + ' ' + u.surname);
              this.eventService
                .getEventsByAuthorUuid(uuid)
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
                .getSquadByUuid(this.user.additionalUserData.squadUuid)
                .subscribe({
                  next: (squad: Squad) => {
                    this.squad = squad;
                  }
                });
            });

          this.eventService
            .getUserEvents(uuid)
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
            .getUserEventsHistory(uuid)
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

  protected readonly e = e;
}
