import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { BooksService } from 'src/services/books.service';
import { ReviewservicesService } from 'src/services/reviewservices.service';
import { bookdto, reviewdto } from '../books/booksinterface';
import { ReviewcomponentComponent } from '../reviewcomponent/reviewcomponent.component';
import { BooksComponent } from '../books/books.component';
import { Observable } from 'rxjs';
import { IssuebookservicesService } from 'src/services/issuebookservices.service';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss'],
})
export class BookDetailsComponent extends BooksComponent implements OnInit {
  bookdetails!: bookdto;
  bookreview$!: Observable<reviewdto[]>;
  bookreviewform!: FormGroup;
  bookidroute!: number;
  islended!:any;
  override loggedUser!: any;

  constructor(
    public override dialog: MatDialog,
    loginService:LoginService,
    private route: ActivatedRoute,
    private bookservice: BooksService,
    private bookissueservice: IssuebookservicesService,
    private bookreviewservice: ReviewservicesService,
    private fb: FormBuilder
  ) {
    super(bookservice, loginService, dialog);
  }

  override ngOnInit(): void {
    this.loggedUser = JSON.parse(localStorage.getItem('user') || '{}');
    const routeParams = this.route.snapshot.paramMap;
    this.bookidroute = Number(routeParams.get('book_id'));
    this.bookservice.getbookbyid(this.bookidroute).subscribe((n) => {
      this.bookdetails = n;
    });
    this.bookreview$ = this.bookservice.getreviewbybookid(this.bookidroute);
    this.bookissueservice.islended(this.bookidroute).subscribe(n=>{
      this.islended = n;
    });
  }

  openDialog(): void {
    this.dialog.open(ReviewcomponentComponent, {
      data: {
        bookTitle: this.bookdetails.book_title,
        bookId: this.bookdetails.book_id,
      },
    });
  }
}
