import { Component, Input, OnInit } from '@angular/core';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.scss']
})
export class BodyComponent implements OnInit {

  public loggedIn=false;

  constructor(private loginService:LoginService){}

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
    this.loggedIn=this.loginService.isLoggedIn();
    if (this.loginService.tokenExpired(this.loginService.getToken())) {
      this.loginService.logout();
      window.location.href = "/login"
    }
}

  @Input() collapsed = false;
  @Input() screenWidth = 0;

  getBodyClass(): string {
    let styleClass = '';
    if(this.collapsed && this.screenWidth > 768) {
      styleClass = 'body-trimmed';
    } else{
      styleClass = 'body-md-screen'
    }
    // if(this.collapsed && this.screenWidth <= 768 && this.screenWidth > 0) 
    return styleClass;
  }
}
