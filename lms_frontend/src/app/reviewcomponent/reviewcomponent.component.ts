import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { ReviewservicesService } from 'src/services/reviewservices.service';
import { reviewadd } from '../books/booksinterface';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';

@Component({
  selector: 'app-reviewcomponent',
  templateUrl: './reviewcomponent.component.html',
  styleUrls: ['./reviewcomponent.component.scss'],
})
export class ReviewcomponentComponent {
  review!: reviewadd;
  starCount!: number;
  bookreviewform!: FormGroup;

  constructor(
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private bookreviewservice: ReviewservicesService
  ) {
    this.bookreviewform = this.fb.group({
      // star: ['', [Validators.min(1), Validators.required, Validators.max(5)]],
      comments: [''],
    });
  }

  addreview() {
    // console.log(this.bookreviewform.get("comments")?.value);
    this.review = {
      star: this.starCount,
      comments: this.bookreviewform.get('comments')?.value,
    };
    console.warn(this.data);
    this.bookreviewservice.addreview(this.data.bookId, this.review).subscribe(
      (n) => {
        this.dialog.open(DialogmodalComponent, {
          data: {
            name: 'Review Added Successfully. ',
            url: '/book-details/' + this.data.bookId,
          },
        });
      },
      (error) => {
        this.dialog.open(DialogmodalComponent, {
          data: {
            name: 'Review failed to add! ',
          },
        });
      }
    );
  }
  star_count(value: number) {
    this.starCount = value;
    // console.log("------------------value : ", value, this.review);
  }
}
