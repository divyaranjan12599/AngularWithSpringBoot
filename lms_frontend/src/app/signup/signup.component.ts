import { Component } from '@angular/core';
import { SidenavService } from 'src/services/sidenav.service';
import { UserService } from 'src/services/user.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  countries: any;
  states: any;
  cities: any;
  users: any;

  constructor(
    private userService: UserService,
    public sidenav: SidenavService
  ) {}

  public userAddress = {
    address: '',
  };
  mobNumberPattern = '^((\\+91-?)|0)?[0-9]{10}$';
  public user = {
    userName: '',
    password: '',
    email: '',
    number: '',
    confirmPassword: '',
    userAddress: {
      address: '',
    },
  };

  ngOnInit() {
    this.sidenav.hide();
    this.userService.getcountry().subscribe((data) => (this.countries = data));
    this.userService.getAllUser().subscribe((data) => {
      this.users = data;
    });
  }

  formSubmit(country: any, state: any, city: any) {
    if (this.userService.duplicateEmail(this.users, this.user.email)) {
      alert('email is already registered! ');
      return;
    }
    if (this.userService.duplicatePhone(this.users, this.user.number)) {
      alert('Number is already registered! ');
      return;
    }

    if (this.user.email == null || this.user.userName == null) {
      alert('email and userName is required !!');
      return;
    }

    this.userService.addUser(this.user, country, state, city).subscribe(
      (data) => {
        alert('success');
        window.location.href = '/login';
      },
      (error) => {
        alert('something went wrong!');
      }
    );
  }
  onChangeCountry(country: String) {
    if (country) {
      this.userService.getstate(country).subscribe((data) => {
        this.states = data;
        this.cities = null;
      });
    } else {
      this.states = null;
      this.cities = null;
    }
  }
  onChangeState(state: any) {
    if (state) {
      this.userService.getcity(state).subscribe((data) => (this.cities = data));
    } else {
      this.cities = null;
    }
  }
}
