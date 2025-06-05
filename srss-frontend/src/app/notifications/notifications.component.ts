import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Title} from '@angular/platform-browser';
import {RefreshService} from '../services/refresh.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  private sub: Subscription = new Subscription();


  constructor(
    private titleService: Title,
    private refreshService: RefreshService
  ) {
  }


  ngOnInit(): void {
    this.titleService.setTitle('Уведомления');

    this.loadData();

    this.sub.add(
      this.refreshService.refresh$.subscribe(() => this.loadData())
    );
  }


  private loadData(): void {
  }
}
