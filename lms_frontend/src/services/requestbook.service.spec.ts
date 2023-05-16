import { TestBed } from '@angular/core/testing';

import { RequestbookService } from './requestbook.service';

describe('RequestbookService', () => {
  let service: RequestbookService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RequestbookService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
