import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ForgetPasswordServicesService } from 'src/services/forget-password-services.service';
import { SidenavService } from 'src/services/sidenav.service';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';

@Component({
  selector: 'app-forgetpassword',
  templateUrl: './forgetpassword.component.html',
  styleUrls: ['./forgetpassword.component.scss'],
})
export class ForgetpasswordComponent implements OnInit {
  emails = {
    email: '',
  };

  constructor(
    public sidenav: SidenavService,
    public forgetService: ForgetPasswordServicesService,
    public dialog: MatDialog
  ) {}
  ngOnInit(): void {
    this.sidenav.hide();
  }
  onSubmit() {
    if (this.emails.email !== '' && this.emails.email !== null) {
      this.forgetService.generateRandomToken(this.emails).subscribe(
        (res: any) => {
          // console.log(res);
          if (res === 'User Not Found') {
            this.dialog.open(DialogmodalComponent, {
              data: {
                name: 'Please Enter correct email address',
                url: '/forgetpassword',
              },
            });
          } else {
            this.dialog.open(DialogmodalComponent, {
              data: {
                name: 'Go and Check Your Email',
                url: '/forgetpassword',
              },
            });
          }
        },
        (error) => {
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Something went wrong!!!',
              url: '/forgetpassword',
            },
          });
        }
      );
    }
  }
}
