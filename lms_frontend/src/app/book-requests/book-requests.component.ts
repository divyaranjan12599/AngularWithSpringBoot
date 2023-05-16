import { Component } from '@angular/core';
import { Observable, Subscribable } from 'rxjs';
import { RequestbookService } from 'src/services/requestbook.service';
import { requestbookdto, isActive } from '../req-book/reqbook';

@Component({
  selector: 'app-book-requests',
  templateUrl: './book-requests.component.html',
  styleUrls: ['./book-requests.component.scss'],
})
export class BookRequestsComponent {
  requestBookdtos$!: Observable<requestbookdto[]>;
  resdata: any;
  requestId: any;
  bookrequests: requestbookdto[] = [];

  constructor(private requestBookService: RequestbookService) {}

  public requestBook = {
    authorName: '',
    bookName: '',
  };

  ngOnInit() {
    // console.log("hi")
    //this.requestBookdtos$=this.requestBookService.getAllRequest();

    this.requestBookService.getAllRequest().subscribe((res) => {
      // console.log(res);
      for (let i = 0; i < res.length; i++) {
        //How to properly iterate here!!
        // console.log(res[i].isActive);
        if (this.check(res[i].isActive)) {
          // console.log(res[i])
          this.bookrequests = [...this.bookrequests, res[i]];
          // console.log(this.bookrequests);
        }
      }
    });
  }

  acceptRequest(requestId: number, value: number) {
    // console.log(requestId);
    // console.log(value);
    this.requestBookService.updateRequest(requestId, value).subscribe((n) => {
      // console.log(n);
    });
    window.location.href = '/book-reqs';
  }

  rejectRequest(requestId: number, value: number) {
    // console.log(requestId);
    // console.log(value);
    this.requestBookService.updateRequest(requestId, value).subscribe((n) => {
      // console.log(n);
    });
    window.location.href = '/book-reqs';
  }

  pendinpurchase(requestId: number, value: number) {
    // console.log(value);
    // console.log(requestId);
    this.requestBookService.updateRequest(requestId, value).subscribe((n) => {
      // console.log(n);
    });
    window.location.href = '/book-reqs';
  }
  check(status: string) {
    //  console.log();
    if (status === 'Pending') {
      // console.log("aa")
      return true;
    } else {
      // console.log()
      // console.log("sks")
      return false;
    }
  }
  key = 'authorName';
  reverse: boolean = false;
  sort(_key: any) {
    this.key = _key;
    this.reverse = !this.reverse;
  }
}
