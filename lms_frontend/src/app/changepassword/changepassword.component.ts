import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ChangePasswordService } from 'src/services/change-password.service';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.scss'],
})
export class ChangepasswordComponent implements OnInit {
  passwords = {
    oldPassword: '',
    newPassword: '',
    confirmPassword: '',
  };
  user_id: number | undefined;
  ngOnInit(): void {}
  constructor(
    private route: ActivatedRoute,
    private changepasswordservice: ChangePasswordService,
    public dialog: MatDialog
  ) {
    this.route.params.subscribe((params) => {
      console.log(params); //log the entire params object
      this.user_id = params['user_id'];
      console.log(this.user_id); //log the value of id
    });
  }
  onSubmit() {
    this.changepasswordservice.changePassword(this.passwords).subscribe(
      (res) => {
        if (res === 'Success') {
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Your Password Is Successfully Changed!.',
              url: '/dashboard',
            },
          });
        }
        if (res === 'Old Password Not Match') {
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Your Old Password Not Matched.',
            },
          });
        }
        console.log(res);
      },
      (error) => {
        console.log(error);
      }
    );
    // console.log(error);
  }
}
