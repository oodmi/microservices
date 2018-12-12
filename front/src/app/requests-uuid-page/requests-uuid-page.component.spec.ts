import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestsUuidPageComponent } from './requests-uuid-page.component';

describe('RequestsUuidPageComponent', () => {
  let component: RequestsUuidPageComponent;
  let fixture: ComponentFixture<RequestsUuidPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestsUuidPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestsUuidPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
