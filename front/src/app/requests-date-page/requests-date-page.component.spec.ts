import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestsDatePageComponent } from './requests-date-page.component';

describe('RequestsDatePageComponent', () => {
  let component: RequestsDatePageComponent;
  let fixture: ComponentFixture<RequestsDatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestsDatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestsDatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
