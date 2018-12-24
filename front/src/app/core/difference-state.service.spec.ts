import { TestBed } from '@angular/core/testing';

import { DifferenceStateService } from './difference-state.service';

describe('DifferenceStateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DifferenceStateService = TestBed.get(DifferenceStateService);
    expect(service).toBeTruthy();
  });
});
