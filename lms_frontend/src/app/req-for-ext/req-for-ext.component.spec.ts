import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReqForExtComponent } from './req-for-ext.component';

describe('ReqForExtComponent', () => {
  let component: ReqForExtComponent;
  let fixture: ComponentFixture<ReqForExtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReqForExtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReqForExtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
