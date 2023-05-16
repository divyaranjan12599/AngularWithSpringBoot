import {
  animate,
  keyframes,
  style,
  transition,
  trigger,
} from '@angular/animations';
import {
  Component,
  Output,
  EventEmitter,
  OnInit,
  HostListener,
} from '@angular/core';
import { LoginService } from 'src/services/login.service';
import { adminNavbarData, userNavbarData } from './nav-data';
import { SidenavService } from 'src/services/sidenav.service';

interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('450ms', style({ opacity: 1 })),
      ]),
      transition(':leave', [
        style({ opacity: 1 }),
        animate('150ms', style({ opacity: 0 })),
      ]),
    ]),
    trigger('rotate', [
      transition(':enter', [
        animate(
          '400ms',
          keyframes([
            style({ transform: 'rotate(0deg)', offset: '0' }),
            style({ transform: 'rotate(1turn)', offset: '1' }),
          ])
        ),
      ]),
    ]),
  ],
})
export class SidenavComponent implements OnInit {
  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  userRole!: String;
  collapsed = false;
  screenWidth = 0;
  navData = adminNavbarData;

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;
    if (this.screenWidth <= 768) {
      this.collapsed = false;
      this.onToggleSideNav.emit({
        collapsed: this.collapsed,
        screenWidth: this.screenWidth,
      });
    }
  }
  public loggedIn = false;
  constructor(
    private loginService: LoginService,
    public sidenav: SidenavService,
    private loginservice: LoginService
  ) {}

  ngOnInit(): void {
    if (this.loginService.isLoggedIn() === false) {
      // console.log("sjs");
    } else {
      this.run();
    }
  }

  toggleCollapse(): void {
    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit({
      collapsed: this.collapsed,
      screenWidth: this.screenWidth,
    });
  }

  closeSidenav(): void {
    this.collapsed = false;
    this.onToggleSideNav.emit({
      collapsed: this.collapsed,
      screenWidth: this.screenWidth,
    });
  }
  logoutUser() {
    this.loginService.logout();
    window.location.href = '/login';
  }

  run() {
    this.screenWidth = window.innerWidth;
    this.loggedIn = this.loginService.isLoggedIn();
    let roles = JSON.parse(localStorage.getItem('role') || '{}');
    // console.log(roles);
    // console.log(this.loginService.isLoggedIn())
    this.userRole = roles[0].roleName;
    // console.log(this.userRole);
    if (this.userRole === 'ADMIN') {
      // console.warn("ADMIN  sddfjs");
      this.navData = [...this.navData, ...userNavbarData];
    } else {
      this.navData = userNavbarData;
    }
    // console.log("")
  }
}
