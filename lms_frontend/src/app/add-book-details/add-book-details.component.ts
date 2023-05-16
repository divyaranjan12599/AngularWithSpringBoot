import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BooksService } from 'src/services/books.service';
import { DialogmodalComponent } from '../dialogmodal/dialogmodal.component';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-add-book-details',
  templateUrl: './add-book-details.component.html',
  styleUrls: ['./add-book-details.component.scss'],
})
export class AddBookDetailsComponent implements OnInit {
  addbookform!: FormGroup;
  category!: FormGroup;
  bookadded!: string;

  loggedUser!: any;

  constructor(
    private fb: FormBuilder,
    private addbookservice: BooksService,
    private loginService: LoginService,
    public dialog: MatDialog,
    public bookservice:BooksService
  ) { }
  categorynames!:string[];


  ngOnInit() {
    this.loggedUser = JSON.parse(localStorage.getItem('user') || '{}');
    this.addbookform = this.fb.group({
      bookName: ['', [Validators.required]],
      quantity: ['', [Validators.min(1), Validators.required]],
      // categoryName:['',[Validators.required]],
      // category: this.fb.group({
      //   categoryName: ['', [Validators.required]],
      // }),
      category: this.fb.array([
        this.fb.group({
          categoryName: ['', [Validators.required]],
        })
      ]),

      authors: this.fb.array([
        this.fb.group({
          authorName: ['', [Validators.required]],
        }),
      ]),
    });


    this.bookservice.getcategorynames("aak").subscribe((n)=>{
      console.log(n);
      this.categorynames=n;
    })

  }
  logoutUser() {
    this.loginService.logout();
    window.location.href = '/login';
  }


  get authorsArray(){
    return this.addbookform.get('authors') as FormArray;
  }

  get categoryArray(){
    return this.addbookform.get('category') as FormArray;
  }

  addauthors() {
    // console.log(this.authorsArray);
    this.authorsArray.push(
      this.fb.group({
        authorName: ['', [Validators.required]],
      })
    );
    // console.warn("sdgadshgasd  ::  ",(this.addbookform.controls['authors'] as FormArray).controls.forEach);
  }

  addcategory() {
    // console.log(this.authorsArray);
    this.categoryArray.push(
      this.fb.group({
        categoryName: ['', [Validators.required]],
      })
    );
    // console.warn("sdgadshgasd  ::  ",(this.addbookform.controls['authors'] as FormArray).controls.forEach);
  }





  removeperson(i: number) {
    // console.log(i);
    if (this.authorsArray.length === 1) {
      // console.log("jsjsj")
    } else {
      this.authorsArray.removeAt(i);
    }
  }

  removecategory(i: number) {
    // console.log(i);
    if (this.categoryArray.length === 1) {
      // console.log("jsjsj")
    } 
    else {
      this.categoryArray.removeAt(i);
    }
  }

  addtobase() {
    console.log("checking..");
    console.log(this.addbookform.getRawValue())
    this.addbookservice
      .addbookdetails(this.addbookform.getRawValue())
      .subscribe(
        (n) => {
          // console.log(n);
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Book was Added Successfully',
              url: '/add-book-details',
            },
          });
          // this.reset();
        },
        (error) => {
          // console.log(error);
          this.dialog.open(DialogmodalComponent, {
            data: {
              name: 'Book was not Added Successfully',
            },
          });
        }
      );
  }

  reset() {
    window.location.reload();
  }

  get firstFormGroupControls() 
  {
    // return this.addbookform.get('category')['controls'];
    return 'jh';
  }
  addtobase1(){
        console.log("ksks");
  }
}
