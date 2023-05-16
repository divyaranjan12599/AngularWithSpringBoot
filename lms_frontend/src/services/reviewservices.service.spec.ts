import { TestBed } from '@angular/core/testing';

import { ReviewservicesService } from './reviewservices.service';

describe('ReviewservicesService', () => {
  let service: ReviewservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReviewservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
