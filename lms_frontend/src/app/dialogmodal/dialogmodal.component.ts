import { Component, Inject } from '@angular/core';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';
@Component({
  selector: 'app-dialogmodal',
  templateUrl: './dialogmodal.component.html',
  styleUrls: ['./dialogmodal.component.scss'],
})
export class DialogmodalComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  refresh() {
    if (this.data.url === undefined || this.data.url === null) {
      // console.log("if to load page");
    } else {
      // console.log("else block is executed");
      window.location.reload();
      // window.location.href = this.data.url;
    }
  }
}
