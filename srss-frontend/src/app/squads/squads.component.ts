import {Component, OnInit} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {Observable, Subscription} from 'rxjs';
import {RefreshService} from '../services/refresh.service';
import {Squad} from '../dto/Squad';
import {SquadService} from '../services/squad.service';
import {SQUADS_SORTED_TYPE_LABELS, SquadsSortedType} from '../enums/SquadsSortedType';
import {FullUser} from '../dto/FullUser';
import {UserService} from '../services/user.service';
import {environment} from '../../environments/environment';
import {User} from '../dto/User';
import {MenuService} from '../services/menu.service';

@Component({
  selector: 'app-squads',
  templateUrl: './squads.component.html',
  styleUrls: ['./squads.component.css']
})
export class SquadsComponent implements OnInit {

  private sub: Subscription = new Subscription();
  user$: Observable<User | null> = this.userService.user$;

  squads: Squad[] = [];
  commanders: Map<string, FullUser> = new Map<string, FullUser>();
  isMenuOpen = true;

  // Все типы сортировки
  sortedTypes: SquadsSortedType[] = Object.values(SquadsSortedType);

  // Значение по умолчанию
  selectedSortType: SquadsSortedType = SquadsSortedType.RATING_ASC;

  constructor(
    private refreshService: RefreshService,
    private titleService: Title,
    private squadService: SquadService,
    private userService: UserService,
    private menuService: MenuService
  ) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Отряды');
    this.loadData();

    this.sub.add(
      this.menuService.open$.subscribe(open => {
        this.isMenuOpen = open;
      })
    );

    this.sub.add(
      this.refreshService.refresh$.subscribe(() => this.loadData())
    );
  }

  private loadData(): void {
    this.squadService
      .getAllSquads(this.selectedSortType)
      .subscribe({
        next: (squads: Squad[]) => {
          this.squads = squads;

          this.squads
            .forEach((squad: Squad) => {
              this.userService
                .getFullUserDataByUuid(squad.commanderUuid)
                .subscribe({
                  next: (user: FullUser) => {
                    this.commanders.set(user.uuid, user);
                  },

                  error: (err) => {
                    console.error(err);
                  }
                });
            });
        },
        error: (err) => {
          console.error(err);
        }
      });
  }

  onSortChange(sort: string): void {
    this.selectedSortType = sort as SquadsSortedType;
    this.loadData();
  }

  // Доступ к labels
  getSortedTypeLabel(type: SquadsSortedType): string {
    return SQUADS_SORTED_TYPE_LABELS[type];
  }

  protected readonly environment = environment;
}

