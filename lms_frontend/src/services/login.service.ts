import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  url = 'http://localhost:8080/authenticate';

  constructor(private http: HttpClient) {}

  // caling the server to generate token

  generateToken(credentials: any) {
    //toekn generate
    return this.http.post(this.url, credentials);
  }

  //for login user
  loginUser(token: any) {
    localStorage.setItem('token', token);
    // console.log(token)
    return true;
  }

  loginUserObject(User: any) {
    localStorage.setItem('user', JSON.stringify(User));
    // console.log(User);
    localStorage.setItem('role', JSON.stringify(User.role));

    return true;
  }

  // to check user is login
  isLoggedIn() {
    let token = localStorage.getItem('token');
    // console.log(token)
    if (token == undefined || token === '' || token == null) {
      // console.log("sss");
      return false;
    } else {
      return true;
    }
  }
  // get logout
  logout() {
    localStorage.clear();
    return true;
  }
  // get token
  getToken() {
    return localStorage.getItem('token');
  }
  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem('roles') || '{}');
  }
  public roleMatch(allowedRoles: any): boolean {
    let isMatch = false;
    const userRoles: any = this.getRoles();
    // console.log(userRoles);

    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].roleName === allowedRoles[j]) {
            isMatch = true;
          }
        }
      }
    }
    return isMatch;
  }
  public tokenExpired(token: any) {
    const expiry = JSON.parse(atob(token.split('.')[1])).exp;
    // console.log(expiry);
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }

  isadmin(): boolean {
    //   if (role === 'USER') {
    //        this.isadmin=false;
    //   }
    //   else if (role==="ADMIN")
    //   {
    //   this.isadmin=true;
    //   console.log(this.isadmin);
    //  }
    //  else{
    //   this.isadmin=false;
    //  }

    if (this.isLoggedIn() === false) {
      return false;
    }

    let savedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    let roles = JSON.parse(localStorage.getItem('role') || '{}');
    //console.log(localStorage.getItem(localStorage.key(2)||""))
    // console.log("zfcvbnm", roles[0].roleName);
    let role = roles[0].roleName;
    if (role === 'USER') {
      return false;
    } else if (role === 'ADMIN') {
      return true;
    } else {
      return false;
    }
  }
}
