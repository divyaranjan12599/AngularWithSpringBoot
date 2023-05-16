import { Component, OnInit } from '@angular/core';
import { Spinkit } from 'ng-http-loader';

interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit{
  title = 'lms';
  spinnerStyle = Spinkit;
  userName!: String;
  loggedPerson!:any;
  isSideNavCollapsed = false;
  screenWidth = 0;

  ngOnInit(): void {
    this.loggedPerson = JSON.parse(localStorage.getItem('user') || '{}');
    this.userName = this.loggedPerson.userName;
  }

  onToggleSideNav(data: SideNavToggle): void {
    this.screenWidth = data.screenWidth;
    this.isSideNavCollapsed = data.collapsed;
  }
}
