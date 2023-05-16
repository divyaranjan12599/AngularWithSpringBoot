import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { requestbookdto } from 'src/app/req-book/reqbook';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';
import baseUrl from './helper';
import { DialogmodalComponent } from 'src/app/dialogmodal/dialogmodal.component';

@Injectable({
  providedIn: 'root',
})
export class RequestbookService {
  listData: any;
  constructor(private http: HttpClient, public dialog: MatDialog) {
    this.listData = [];
  }

  addRequestBookDetails(requestBook: any, resdata: any) {
    // console.log(localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:"+savedPerson.userId);
    for (var v in resdata) {
      // console.log(resdata[v]);
      if (resdata[v].bookName === requestBook.bookName) {
        this.dialog.open(DialogmodalComponent, {
          data: {
            name: 'This book is already requested!!',
          },
        });
        return requestBook;
      }
    }
    // console.log("postmapping works!")
    const usersUrl = 'http://localhost:8080/requestbook/' + savedPerson.userId;
    return this.http.post(usersUrl, requestBook, {
      headers,
      responseType: 'text' as 'json',
    });
  }

  getRequest() {
    // console.log(localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:"+savedPerson.userId);
    const usersUrl =
      'http://localhost:8080/requestBookdto/' + savedPerson.userId;
    return this.http.get<requestbookdto[]>(usersUrl, { headers });
  }
  getAllRequest() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    return this.http.get<requestbookdto[]>(
      'http://localhost:8080/requestBookdto',
      { headers }
    );
  }

  updateRequest(requestId: number, status: number) {
    // console.log(status);
    // console.log(localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    const requrl =
      'http://localhost:8080/isAccepted/admin/' + requestId + '/' + status;
    // console.log(requrl);
    return this.http.get<any>(requrl, { headers });
  }
}
