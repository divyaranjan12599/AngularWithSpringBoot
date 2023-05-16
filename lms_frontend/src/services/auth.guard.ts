import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    //console.log(this.loginService.isadmin());
    if (this.loginService.isLoggedIn()) {
      // console.log(this.loginService.isLoggedIn())
      return true;
    } else {
      // console.log("in the auth guard")
      //return false;
      this.loginService.logout();
      return this.router.navigate(['login']);
    }
    // this.router.navigate(['login']);
    //   return false;
  }
}
