import { Component, Input, OnInit } from '@angular/core';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{

  loggedUser!: any;

  @Input()
  heading!:string

  constructor(
    private loginService: LoginService,
  ) { }

  ngOnInit(): void {
    this.loggedUser = JSON.parse(localStorage.getItem('user') || '{}');
    console.log(this.heading);
  }

  logoutUser() {
    this.loginService.logout();
    window.location.href = '/login';
  }
}

