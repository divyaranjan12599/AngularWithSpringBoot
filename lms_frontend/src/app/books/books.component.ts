import { Component, OnInit, ViewChild } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BooksService } from 'src/services/books.service';
import { bookdto } from './booksinterface';
import { Router } from '@angular/router';
import { BookDetailsComponent } from '../book-details/book-details.component';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss'],
})
export class BooksComponent implements OnInit {
  bookdetails$!: Observable<bookdto[]>;
  accordingto!: string;
  bookdetailscount$!: Observable<number>;
  count!: number;
  router!: Router;
  order = true;
  loggedUser!: any;

  ngOnInit() {
    this.loggedUser = JSON.parse(localStorage.getItem('user') || '{}');
    this.bookdetails$ = this.getbookdetailsservice.geteverybookdetails();
  }

  constructor(
    private getbookdetailsservice: BooksService,
    private loginService: LoginService,
    public dialog: MatDialog
  ) {}

  logoutUser() {
    this.loginService.logout();
    window.location.href = '/login';
  }

  getdetails() {
    this.bookdetails$ = this.getbookdetailsservice.getbookdetailssearch(
      this.accordingto
    );
  }

  getBookDetails() {
    this.router.navigateByUrl('/book-details');
  }

  fun() {
    alert('ashg');
  }

  lendbook(book_id: number) {
    this.getbookdetailsservice.lendbook(book_id).subscribe(
      (n) => {
        this.dialog.open(DialogmodalComponent, {
          data: {
            name: n,
          },
        });
      },
      (error) => {
        this.dialog.open(DialogmodalComponent, {
          data: {
            name: "you can't lend the book",
          },
        });
      }
    );
  }

  dec_to_int(n: number) {
    return ~~n;
  }

  dec_part(n: number) {
    let dec_part = Math.abs(n) - Math.floor(n);
    if (dec_part >= 0.5) {
      return 1;
    }
    return 0;
  }

  sortDataByTitle() {
    if (this.order) {
      this.bookdetails$ = this.bookdetails$.pipe(
        map((bookdetails) => {
          bookdetails.sort((a, b) => {
            return a.book_title.toLowerCase() < b.book_title.toLowerCase()
              ? -1
              : 1;
          });
          return bookdetails;
        })
      );
      this.order = !this.order;
    } else {
      this.bookdetails$ = this.bookdetails$.pipe(
        map((bookdetails) => {
          bookdetails.sort((a, b) => {
            return a.book_title.toLowerCase() > b.book_title.toLowerCase()
              ? -1
              : 1;
          });
          return bookdetails;
        })
      );
      this.order = !this.order;
    }
  }
  sortDataByQuantity() {
    if (this.order) {
      this.bookdetails$ = this.bookdetails$.pipe(
        map((bookdetails) => {
          bookdetails.sort((a, b) => {
            return a.quantity < b.quantity ? -1 : 1;
          });
          return bookdetails;
        })
      );
      this.order = !this.order;
    } else {
      this.bookdetails$ = this.bookdetails$.pipe(
        map((bookdetails) => {
          bookdetails.sort((a, b) => {
            return a.quantity > b.quantity ? -1 : 1;
          });
          return bookdetails;
        })
      );
      this.order = !this.order;
    }
  }

  sortDataByAuthors() {
    //   if (this.order) {
    //     this.bookdetails$ = this.bookdetails$.pipe(map((bookdetails) => {
    //       bookdetails.sort((a, b) => {
    //         console.log(a.book_title.toLowerCase(), b.book_title.toLowerCase())
    //         return a.book_title.toLowerCase() < b.book_title.toLowerCase() ? -1 : 1;
    //       });
    //       return bookdetails;
    //     }))
    //     this.order = !this.order;
    //   }
    //   else {
    //     this.bookdetails$ = this.bookdetails$.pipe(map((bookdetails) => {
    //       bookdetails.sort((a, b) => {
    //         console.log(a.book_title.toLowerCase(), b.book_title.toLowerCase())
    //         return a.book_title.toLowerCase() > b.book_title.toLowerCase() ? -1 : 1;
    //       });
    //       return bookdetails;
    //     }))
    //     this.order = !this.order;
    //   }
    // }

    // sortDataByRating() {
    //   if (this.order) {
    //     this.bookdetails$ = this.bookdetails$.pipe(map((bookdetails) => {
    //       bookdetails.sort((a, b) => {
    //         console.log(a.book_title.toLowerCase(), b.book_title.toLowerCase())
    //         return a.book_title.toLowerCase() < b.book_title.toLowerCase() ? -1 : 1;
    //       });
    //       return bookdetails;
    //     }))
    //     this.order = !this.order;
    //   }
    //   else {
    //     this.bookdetails$ = this.bookdetails$.pipe(map((bookdetails) => {
    //       bookdetails.sort((a, b) => {
    //         console.log(a.book_title.toLowerCase(), b.book_title.toLowerCase())
    //         return a.book_title.toLowerCase() > b.book_title.toLowerCase() ? -1 : 1;
    //       });
    //       return bookdetails;
    //     }))
    //     this.order = !this.order;
    //   }
    alert('Need to be implemented!!!');
  }

  sortDataByCategory() {
    // this.order = true;
    // if (this.order) {
    //   this.bookdetails$ = this.bookdetails$.pipe(map((bookdetails) => {
    //     bookdetails.sort((a, b) => {
    //       console.log(a.book_title.toLowerCase(), b.book_title.toLowerCase())
    //       return a.book_title.toLowerCase() < b.book_title.toLowerCase() ? -1 : 1;
    //     });
    //     return bookdetails;
    //   }))
    //   this.order = !this.order;
    // }
    // else {
    //   this.bookdetails$ = this.bookdetails$.pipe(map((bookdetails) => {
    //     bookdetails.sort((a, b) => {
    //       console.log(a.book_title.toLowerCase(), b.book_title.toLowerCase())
    //       return a.book_title.toLowerCase() > b.book_title.toLowerCase() ? -1 : 1;
    //     });
    //     return bookdetails;
    //   }))
    //   this.order = !this.order;
    // }
    alert('Need to be implemented!!!');
  }

  sortDataByRating() {
    if (this.order) {
      this.bookdetails$ = this.bookdetails$.pipe(
        map((bookdetails) => {
          bookdetails.sort((a, b) => {
            return a.avg_rating < b.avg_rating ? -1 : 1;
          });
          return bookdetails;
        })
      );
      this.order = !this.order;
    } else {
      this.bookdetails$ = this.bookdetails$.pipe(
        map((bookdetails) => {
          bookdetails.sort((a, b) => {
            console.log(a.book_title.toLowerCase(), b.book_title.toLowerCase());
            return a.avg_rating > b.avg_rating ? -1 : 1;
          });
          return bookdetails;
        })
      );
      this.order = !this.order;
    }
  }
  key = 'book_title';
  reverse: boolean = false;
  sort(_key: any) {
    this.key = _key;
    this.reverse = !this.reverse;
  }
}
