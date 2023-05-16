import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { reviewadd } from 'src/app/books/booksinterface';

@Injectable({
  providedIn: 'root',
})
export class ReviewservicesService {
  constructor(private httpclient: HttpClient) {}

  addreview(bookid: number, reviewdetails: reviewadd) {
    // console.log(reviewdetails);
    // console.log(bookid);
    // console.log("fghjkgfhjk",localStorage.getItem("token"));
    // console.log("Bearer " + localStorage.getItem("token"));

    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );

    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:" + savedPerson.userId);
    // console.log(headers.get("Authorization"));

    const url =
      'http://localhost:8080/addreviewbyids/' +
      savedPerson.userId +
      '/' +
      bookid;

    return this.httpclient.post(url, reviewdetails, {
      headers,
      responseType: 'text' as 'json',
    });
  }
}
