import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewcomponentComponent } from './reviewcomponent.component';

describe('ReviewcomponentComponent', () => {
  let component: ReviewcomponentComponent;
  let fixture: ComponentFixture<ReviewcomponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewcomponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewcomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
