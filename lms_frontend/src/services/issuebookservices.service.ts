import { JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { issuebookdetails } from 'src/app/books/booksinterface';

@Injectable({
  providedIn: 'root',
})
export class IssuebookservicesService {
  isExtendable:Boolean=true;
    extenddatebook(issueId: number) {
    // console.log(issueId);
    // console.log(localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    //  console.log(headers.get("Authorization"));
    const usersUrl = 'http://localhost:8080/addextension/' + issueId;
    return this.httpclient.get<any>(usersUrl, {
      headers,
      responseType: 'text' as 'json',
    });
  }
  returnbook(issueId: number) {
    // console.log(issueId);
    // console.log(localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    //  console.log(headers.get("Authorization"));
    const usersUrl = 'http://localhost:8080/return/' + issueId;
    return this.httpclient.get<any>(usersUrl, {
      headers,
      responseType: 'text' as 'json',
    });
  }

  constructor(private httpclient: HttpClient) {}

  getallIssuebookdetails() {
    // console.log(localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    //console.log("user details"+localStorage.getItem("user"))
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:"+savedPerson.userId);
    const usersUrl = 'http://localhost:8080/totalBooks/' + savedPerson.userId;
    return this.httpclient.get<any>(usersUrl, { headers });
  }

  getreadIssuebookdetails() {
    // console.log(localStorage.getItem("token"));
    // console.log("read issued book details is called")
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:"+savedPerson.userId);
    const usersUrl = 'http://localhost:8080/readBooks/' + savedPerson.userId;
    return this.httpclient.get<any>(usersUrl, { headers });
  }

  getIssuedbookdetalis() {
    // console.log(localStorage.getItem("token"));
    // console.log("read issued book details is called")
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    let roles = JSON.parse(localStorage.getItem('role') || '{}');
    //console.log(localStorage.getItem(localStorage.key(2)||""))

    // console.log("zfcvbnm",roles[0].roleName);
    const usersUrl = 'http://localhost:8080/issuedBooks/' + savedPerson.userId;
    return this.httpclient.get<any>(usersUrl, { headers });
  }

  getallpendingbookdetalis() {
    // console.log(localStorage.getItem("token"));
    // console.log("read issued book details is called")
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:"+savedPerson.userId);
    const usersUrl = 'http://localhost:8080/pendingBooks/' + savedPerson.userId;
    return this.httpclient.get<any>(usersUrl, { headers });
  }

  getallusersissuedbookdetails() {
    // console.log(localStorage.getItem("token"));
    // console.log("read issued book details is called")
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    const usersUrl = 'http://localhost:8080/getAllIssued';
    return this.httpclient.get<any>(usersUrl, { headers });
  }

  checkExtension(issuedId : any){
    return this.httpclient.get<any>('http://localhost:8080/findByIssueId/'+issuedId);
  }

  islended(bookId : any){
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    let loggedUser = JSON.parse(localStorage.getItem('user') || '{}');
    return this.httpclient.get<any>('http://localhost:8080/isBookLended/'+loggedUser.userId+"/"+bookId,{ headers });
  }

  withdrawbook(issue_id: number) 
  {
    // console.log(localStorage.getItem("token"));
    // console.log("Bearer " + localStorage.getItem("token"));
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:" + savedPerson.userId);
    // console.log(headers.get("Authorization"));
    const url = 'http://localhost:8080/withdrawextension/';
    return this.httpclient.get<any>(url + issue_id, {
      headers,
      responseType: 'text' as 'json',
    });
  }







}
