import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResetpasswordComponent } from 'src/app/resetpassword/resetpassword.component';

@Injectable({
  providedIn: 'root',
})
export class ResetPasswordService {
  constructor(private http: HttpClient) {}
  userurl: any;
  CheckToken(tokens: any) {
    this.userurl = 'http://localhost:8080/forgetReset_password?token=' + tokens;
    return this.http.get<any>(this.userurl, { responseType: 'text' as 'json' });
  }
  changePassword(credentials: any, tokens: any) {
    this.userurl = 'http://localhost:8080/forgetReset_password?token=' + tokens;

    return this.http.post(this.userurl, credentials, {
      responseType: 'text' as 'json',
    });
  }
}
