import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { extension } from 'src/app/books/booksinterface';

@Injectable({
  providedIn: 'root',
})
export class RequestextensiondateService {
  constructor(private httpclient: HttpClient) {}

  getallextensionrequestiondates() {
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
    const usersUrl = 'http://localhost:8080/getenddateextensions';
    return this.httpclient.get<extension[]>(usersUrl, { headers });
  }

  acceptandrejectextension(value: number, issueid: number) {
    // console.log(issueid);
    // console.log(localStorage.getItem("token"));
    // console.log("read issued book details is called")
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    const usersUrl =
      'http://localhost:8080/accandrejectextension/' + value + '/' + issueid;
    // console.log(usersUrl);
    return this.httpclient.get<any>(usersUrl, {
      headers,
      responseType: 'text' as 'json',
    });
  }





  
}
