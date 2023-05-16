import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { book, bookdto, reviewdto } from 'src/app/books/booksinterface';

@Injectable({
  providedIn: 'root',
})
export class BooksService {
  constructor(private httpclient: HttpClient) {}

  getbookdetailssearch(according: string) {
    // console.log(according.length);
    if (according.length == 0) {
      // console.log("if block is executed");
      return this.geteverybookdetails();
    } else {
      // console.log("else block is executed ")
      // console.log(localStorage.getItem("token"));
      const headers = new HttpHeaders().set(
        'Authorization',
        'Bearer ' + localStorage.getItem('token')
      );
      // console.log(headers.get("Authorization"));
      return this.httpclient.get<bookdto[]>(
        'http://localhost:8080/getbookdetails/' + according,
        { headers }
      );
    }
  }

  addbookdetails(bookdetails: book) {
    // console.log(localStorage.getItem("token"));
    // console.log(bookdetails);
    // console.log("Bearer " + localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    return this.httpclient.post(
      'http://localhost:8080/addbookDetails',
      bookdetails,
      { headers, responseType: 'text' as 'json' }
    );
  }
  geteverybookdetails() {
    // console.log(localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );

    // console.log(headers.get("Authorization"));

    return this.httpclient.get<bookdto[]>(
      'http://localhost:8080/getevrybooks',
      { headers }
    );
  }

  lendbook(book_id: number) {
    // console.log(localStorage.getItem("token"));
    // console.log("Bearer " + localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:" + savedPerson.userId);
    // console.log(headers.get("Authorization"));
    const url = 'http://localhost:8080/lend/' + savedPerson.userId;
    return this.httpclient.get<any>(url + '/' + book_id, {
      headers,
      responseType: 'text' as 'json',
    });
  }

  getbookbyid(book_id: number) {
    // console.log(localStorage.getItem("token"));
    // console.log("Bearer " + localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    const url = 'http://localhost:8080/getbookdetailsbyid/' + book_id;
    return this.httpclient.get<bookdto>(url, { headers });
  }

  getreviewbybookid(book_id: number) {
    // console.log(localStorage.getItem("token"));
    // console.log("Bearer " + localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    const url = 'http://localhost:8080/getreviewbybookid/' + book_id;
    return this.httpclient.get<reviewdto[]>(url, { headers });
  }


  getcategorynames(name:string) {
    // console.log(localStorage.getItem("token"));
    // console.log("Bearer " + localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    const url = 'http://localhost:8080/getcategories/'+name;
    return this.httpclient.get<any>(url, { headers });
  }

  
}
