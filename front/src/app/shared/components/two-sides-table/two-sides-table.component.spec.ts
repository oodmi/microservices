import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TwoSidesTableComponent } from './two-sides-table.component';

describe('TwoSidesTableComponent', () => {
  let component: TwoSidesTableComponent;
  let fixture: ComponentFixture<TwoSidesTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TwoSidesTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TwoSidesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
