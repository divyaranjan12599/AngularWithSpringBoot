import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { IssuebookservicesService } from 'src/services/issuebookservices.service';

@Component({
  selector: 'app-issue-book-details',
  templateUrl: './issue-book-details.component.html',
  styleUrls: ['./issue-book-details.component.scss'],
})
export class IssueBookDetailsComponent implements OnInit {
  bookissuedetails$!: Observable<any>;

  ngOnInit() {
    this.bookissuedetails$ =
      this.issuebookservice.getallusersissuedbookdetails();
  }

  constructor(private issuebookservice: IssuebookservicesService) {}
  key = 'book_title';
  reverse: boolean = false;
  sort(_key: any) {
    this.key = _key;
    this.reverse = !this.reverse;
  }
}
