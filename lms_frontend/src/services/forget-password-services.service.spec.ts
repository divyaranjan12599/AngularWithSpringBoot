import { TestBed } from '@angular/core/testing';

import { ForgetPasswordServicesService } from './forget-password-services.service';

describe('ForgetPasswordServicesService', () => {
  let service: ForgetPasswordServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ForgetPasswordServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
