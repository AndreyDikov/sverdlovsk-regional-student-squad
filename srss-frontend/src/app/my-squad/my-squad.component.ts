import {Component, OnDestroy, OnInit} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {Observable, Subscription} from 'rxjs';
import {RefreshService} from '../services/refresh.service';
import {Squad} from '../dto/Squad';
import {SquadService} from '../services/squad.service';
import {UserService} from '../services/user.service';
import {User} from '../dto/User';
import {FullUser} from '../dto/FullUser';
import {hasRole} from '../functions/hasRoleFunction';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-my-squad',
  templateUrl: './my-squad.component.html',
  styleUrls: ['./my-squad.component.css']
})
export class MySquadComponent implements OnInit, OnDestroy {
  protected readonly hasRole = hasRole;
  user$: Observable<User | null> = this.userService.user$;
  private sub = new Subscription();

  squad: Squad | null = null;
  commander: FullUser | null = null;
  squadMembers: FullUser[] = [];

  // Новые поля
  form!: FormGroup;
  isFormVisible = false;
  isEditing = false;

  constructor(
    private fb: FormBuilder,
    private refreshService: RefreshService,
    private titleService: Title,
    private squadService: SquadService,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Мой отряд');

    // Инициализируем форму
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: ['']
    });

    this.loadData();
    this.sub.add(
      this.refreshService.refresh$.subscribe(() => this.loadData())
    );
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  private loadData(): void {
    this.squadService.getMySquad().subscribe({
      next: squad => {
        this.squad = squad;
        this.fetchCommander();
        this.fetchMembers();
        // если данные появились — скрываем форму
        this.isFormVisible = false;
      },
      error: err => {
        console.error(err);
        this.squad = null;
      }
    });
  }

  private fetchCommander(): void {
    if (!this.squad) {
      return;
    }
    this.userService.getFullUserDataByUuid(this.squad.commanderUuid)
      .subscribe({next: cmd => this.commander = cmd, error: console.error});
  }

  private fetchMembers(): void {
    if (!this.squad) {
      return;
    }
    this.userService.getUsersBySquadUuid(this.squad.uuid)
      .subscribe({next: members => this.squadMembers = members, error: console.error});
  }

  // Показываем форму создания или редактирования
  showForm(edit: boolean = false): void {
    this.isEditing = edit;
    this.isFormVisible = true;
    if (edit && this.squad) {
      this.form.patchValue({
        name: this.squad.name,
        description: this.squad.description
      });
    } else {
      this.form.reset();
    }
  }

  hideForm(): void {
    this.isFormVisible = false;
  }

  // Отправка формы
  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    const payload = {...this.form.value} as Partial<Squad>;
    const request$ = this.isEditing && this.squad
      ? this.squadService.updateSquad(payload)
      : this.squadService.createSquad(payload);

    request$.subscribe({
      next: () => this.loadData(),
      error: console.error
    });
  }
}
