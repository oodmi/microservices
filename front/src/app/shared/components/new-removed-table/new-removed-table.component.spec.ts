import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRemovedTableComponent } from './new-removed-table.component';

describe('NewRemovedTableComponent', () => {
  let component: NewRemovedTableComponent;
  let fixture: ComponentFixture<NewRemovedTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRemovedTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRemovedTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
