import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) { }

  public addUser(user: any, country: any, state: any, city: any) {
    return this.http.post('http://localhost:8080/user/signUp', user, {
      params: {
        countryname: country,
        statename: state,
        cityname: city,
      },
    });
  }

  public updateUser(uid: any, username: any, address: any, country: any, state: any, city: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    return this.http.put<any>('http://localhost:8080/update', savedPerson, {
      params: {
        username: username,
        address: address,
        countryname: country,
        statename: state,
        cityname: city,
      },
      headers:headers,
      responseType: 'text' as 'json',
    });
  }

  public getcountry() {
    return this.http.get('http://localhost:8080/country');
  }

  public getstate(country: any) {
    return this.http.get('http://localhost:8080/state', {
      params: {
        countryname: country,
      },
    });
  }

  public getcity(state: any) {
    return this.http.get('http://localhost:8080/city', {
      params: {
        statename: state,
      },
    });
  }
  getAllUser() {
    return this.http.get(
      'http://localhost:8080/users',
    );
  }

  duplicateEmail(users: any, userEmail: any) {
    for (var v in users) {
      if (users[v].email === userEmail) {
        return true;
      }
    }
    return false;

  }
  duplicatePhone(users: any, userPhone: any) {
    let val1d = parseInt(userPhone)
    for (var v in users) {
      if (val1d === users[v].number) {
        return true;
      }
    }
    return false;
  }
}
