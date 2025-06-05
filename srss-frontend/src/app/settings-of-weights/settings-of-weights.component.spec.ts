import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsOfWeightsComponent } from './settings-of-weights.component';

describe('SettingsOfWeightsComponent', () => {
  let component: SettingsOfWeightsComponent;
  let fixture: ComponentFixture<SettingsOfWeightsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsOfWeightsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SettingsOfWeightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
