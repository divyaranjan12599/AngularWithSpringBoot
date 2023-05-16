import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueBookDetailsComponent } from './issue-book-details.component';

describe('IssueBookDetailsComponent', () => {
  let component: IssueBookDetailsComponent;
  let fixture: ComponentFixture<IssueBookDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssueBookDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IssueBookDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
