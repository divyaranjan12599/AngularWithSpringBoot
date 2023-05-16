import { TestBed } from '@angular/core/testing';

import { IssuebookservicesService } from './issuebookservices.service';

describe('IssuebookservicesService', () => {
  let service: IssuebookservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IssuebookservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
