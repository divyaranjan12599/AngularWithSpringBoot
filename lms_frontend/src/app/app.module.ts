import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BodyComponent } from './body/body.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { BooksComponent } from './books/books.component';
import { ReqBookComponent } from './req-book/req-book.component';
import { MatCardModule } from '@angular/material/card';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DialogmodalComponent } from './dialogmodal/dialogmodal.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { ForgetpasswordComponent } from './forgetpassword/forgetpassword.component';
import { IssueBookDetailsComponent } from './issue-book-details/issue-book-details.component';
import { AddBookDetailsComponent } from './add-book-details/add-book-details.component';
import { BookRequestsComponent } from './book-requests/book-requests.component';
import { ReqForExtComponent } from './req-for-ext/req-for-ext.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ReviewcomponentComponent } from './reviewcomponent/reviewcomponent.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { FooterComponentComponent } from './footer-component/footer-component.component';
import { ValidateEqualModule } from 'ng-validate-equal';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { HeaderComponent } from './header/header.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { Ng2OrderModule } from 'ng2-order-pipe';

@NgModule({
  declarations: [
    AppComponent,
    BodyComponent,
    SidenavComponent,
    DashboardComponent,
    BooksComponent,
    ReqBookComponent,
    LoginComponent,
    SignupComponent,
    BookDetailsComponent,
    DialogmodalComponent,
    ForgetpasswordComponent,
    IssueBookDetailsComponent,
    AddBookDetailsComponent,
    BookRequestsComponent,
    ReqForExtComponent,
    ReviewcomponentComponent,
    ResetpasswordComponent,
    FooterComponentComponent,
    UserprofileComponent,
    HeaderComponent,
    ChangepasswordComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    MatCardModule,
    MatIconModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatButtonModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatExpansionModule,
    NgHttpLoaderModule.forRoot(),
    ValidateEqualModule,
    Ng2OrderModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
