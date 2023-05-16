import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ResetPasswordService } from 'src/services/reset-password.service';
import { SidenavService } from 'src/services/sidenav.service';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.scss'],
})
export class ResetpasswordComponent implements OnInit {
  passwords = {
    newPassword: '',
    confirmPassword: '',
  };
  tokens: any;
  constructor(
    public sidenav: SidenavService,
    private route: ActivatedRoute,
    private resetservice: ResetPasswordService,
    public dialog: MatDialog
  ) {
    this.route.queryParams.subscribe((param) => {
      this.tokens = param['token'];
      // console.log(this.tokens);
    });
  }
  ngOnInit(): void {
    this.sidenav.hide();
    this.resetservice.CheckToken(this.tokens).subscribe(
      (res: any) => {
        // console.log(res);
        if (res === 'Invalid Token') {
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Invalid Token.',
              url: '/login',
            },
          });
        }
      },
      (error) => {
        this.dialog.open(DialogmodalComponent, {
          data: {
            name: 'Invalid Token or Your token is expire.',
            url: '/forgetpassword',
          },
        });
        // console.log(error);
      }
    );
  }

  onSubmit() {
    if (
      this.passwords.newPassword !== '' &&
      this.passwords.newPassword !== null &&
      this.passwords.confirmPassword !== null &&
      this.passwords.confirmPassword !== ''
    ) {
      this.resetservice.changePassword(this.passwords, this.tokens).subscribe(
        (res: any) => {
          // console.log(res);
          if (res === 'success Your password is reset successfully') {
            this.dialog.open(DialogmodalComponent, {
              data: {
                name: 'Your Password is Successfully Changed.',
                url: '/login',
              },
            });
          } else if (res === 'Enter Same password') {
            this.dialog.open(DialogmodalComponent, {
              data: {
                name: 'Enter Same Password.',
              },
            });
          }
        },
        (error) => {
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Your Token is Expired.',
              url: '/forgetpassword',
            },
          });
          // console.log(error);
        }
      );
    }
  }
}
