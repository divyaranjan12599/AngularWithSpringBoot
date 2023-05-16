import { TestBed } from '@angular/core/testing';

import { RequestextensiondateService } from './requestextensiondate.service';

describe('RequestextensiondateService', () => {
  let service: RequestextensiondateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RequestextensiondateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
