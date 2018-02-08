import { TestBed, inject } from '@angular/core/testing';

import { SaladService } from './salad.service';

describe('SaladService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SaladService]
    });
  });

  it('should be created', inject([SaladService], (service: SaladService) => {
    expect(service).toBeTruthy();
  }));
});
