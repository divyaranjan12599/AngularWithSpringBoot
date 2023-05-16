import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestbookService } from 'src/services/requestbook.service';
import { requestbookdto } from './reqbook';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';

@Component({
  selector: 'app-req-book',
  templateUrl: './req-book.component.html',
  styleUrls: ['./req-book.component.scss'],
})
export class ReqBookComponent {
  requestbookdetails$!: Observable<requestbookdto[]>;
  accordingto!: string;
  bookdetailscount$!: Observable<number>;
  count!: number;
  requestbookdto?: requestbookdto[];
  resdata: any;
  dataset: any;
  authorName: any;
  bookName: any;
  requestBookdtos$!: Observable<requestbookdto[]>;

  constructor(
    private requestBookService: RequestbookService,
    public dialog: MatDialog
  ) {}

  public requestBook = {
    authorName: '',
    bookName: '',
  };

  ngOnInit() {
    this.requestBookdtos$ = this.requestBookService.getRequest();
    this.requestBookService.getAllRequest().subscribe((res) => {
      // console.log("okokokokoko")
      this.resdata = res;
    });
  }

  formSubmit() {
    // alert("Request Submitted");
    // console.log(this.requestBook);

    // console.log("okhai");
    // console.log(this.resdata);

    //adding request
    this.requestBookService
      .addRequestBookDetails(this.requestBook, this.resdata)
      .subscribe(
        (data: any) => {
          // console.log(data);
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'sucess',
              url: '/req-book',
            },
          });
        },
        (error: any) => {
          // console.log(error);
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'something went wrong',
            },
          });
        }
      );
  }
  key = 'a';
  reverse: boolean = false;
  sort(_key: any) {
    this.key = _key;
    this.reverse = !this.reverse;
  }
}
