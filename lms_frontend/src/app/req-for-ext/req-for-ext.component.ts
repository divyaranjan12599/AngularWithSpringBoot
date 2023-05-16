import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { RequestextensiondateService } from 'src/services/requestextensiondate.service';
import { bookdto, extension } from '../books/booksinterface';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';

@Component({
  selector: 'app-req-for-ext',
  templateUrl: './req-for-ext.component.html',
  styleUrls: ['./req-for-ext.component.scss'],
})
export class ReqForExtComponent implements OnInit {
  requestextension$!: Observable<extension[]>;

  constructor(
    private requestextensionservice: RequestextensiondateService,
    public dialog: MatDialog
  ) {}
  ngOnInit(): void {
    // this.requestextensionservice.getallextensionrequestiondates().subscribe((n)=>{
    //   console.log(n);
    // })

    this.requestextension$ =
      this.requestextensionservice.getallextensionrequestiondates();
  }

  extensionrequest(value: number, issueid: number) {
    // console.log(value);
    // console.log(issueid);
    this.requestextensionservice
      .acceptandrejectextension(value, issueid)
      .subscribe(
        (n) => {
          // console.log(n);
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: n,
              url: '/reqs-for-ext',
            },
          });
          // console.log("check");
          //window.location.href = "/reqs-for-ext"
        },
        (error) => {
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Error say to your Developer to solve ',
            },
          });
        }
      );
  }
}
