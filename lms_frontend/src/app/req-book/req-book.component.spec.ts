import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReqBookComponent } from './req-book.component';

describe('ReqBookComponent', () => {
  let component: ReqBookComponent;
  let fixture: ComponentFixture<ReqBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReqBookComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReqBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
