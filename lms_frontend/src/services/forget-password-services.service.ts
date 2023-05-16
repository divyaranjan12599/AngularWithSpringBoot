import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ForgetPasswordServicesService {
  url = 'http://localhost:8080/forgetPassword';

  constructor(private http: HttpClient) {}
  generateRandomToken(credentials: any) {
    return this.http.post(this.url, credentials, {
      responseType: 'text' as 'json',
    });
  }
}
