import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UuidPageComponent } from './uuid-page.component';

describe('UuidPageComponent', () => {
  let component: UuidPageComponent;
  let fixture: ComponentFixture<UuidPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UuidPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UuidPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
