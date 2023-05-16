import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.scss']
})
export class UserprofileComponent implements OnInit {
  loggedUser!: any;
  editMode = false;
  countries: any;
  states: any;
  cities: any;

  public data = {
    userName: String,
    address: String,
  }

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.loggedUser = JSON.parse(localStorage.getItem('user') || '{}');
    console.warn(this.loggedUser);
    this.userService.getcountry().subscribe((data) => (this.countries = data));
  }

  updateUser(userName: any, address: any, country: any, state: any, city: any) {
    // console.log("checking..");

    // console.warn("sjsahjafsd-------", country, state, city, userName, address);

    this.userService.updateUser(this.loggedUser.userId, userName, address, country, state, city).subscribe(
      (data) => {
        alert('Data successfully updated...');
        window.location.reload();
      },
      (error) => {
        alert('something went wrong!');
      }
    );
}

editModeOn() {
  this.editMode = true;
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


