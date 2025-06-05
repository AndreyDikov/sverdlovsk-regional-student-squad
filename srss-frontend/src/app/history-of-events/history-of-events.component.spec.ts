import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryOfEventsComponent } from './history-of-events.component';

describe('HistoryOfEventsComponent', () => {
  let component: HistoryOfEventsComponent;
  let fixture: ComponentFixture<HistoryOfEventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryOfEventsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryOfEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
