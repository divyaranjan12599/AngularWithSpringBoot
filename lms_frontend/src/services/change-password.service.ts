import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ChangePasswordService {
  constructor(private http: HttpClient) {}
  public changePassword(password: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    // console.log(headers.get("Authorization"));
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    // console.log("user Id:"+savedPerson.userId)
    // console.log("postmapping works!")
    const usersUrl =
      'http://localhost:8080/changePassword/' + savedPerson.userId;
    return this.http.post(usersUrl, password, {
      headers,
      responseType: 'text' as 'json',
    });
  }
}
