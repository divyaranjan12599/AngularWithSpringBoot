import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BooksComponent } from './books/books.component';
import { ReqBookComponent } from './req-book/req-book.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from 'src/services/auth.guard';

import { SidenavComponent } from './sidenav/sidenav.component';
import { SignupComponent } from './signup/signup.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { ForgetpasswordComponent } from './forgetpassword/forgetpassword.component';
import { IssueBookDetailsComponent } from './issue-book-details/issue-book-details.component';
import { BookRequestsComponent } from './book-requests/book-requests.component';
import { AddBookDetailsComponent } from './add-book-details/add-book-details.component';
import { ReqForExtComponent } from './req-for-ext/req-for-ext.component';
import { AdminguardGuard } from 'src/services/adminguard.guard';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';

const routes: Routes = [
  { path: '', component: LoginComponent, pathMatch: 'full' },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuard],
  },
  { path: 'books', component: BooksComponent, canActivate: [AuthGuard] },
  { path: 'req-book', component: ReqBookComponent, canActivate: [AuthGuard] },
  {
    path: 'book-details/:book_id',
    component: BookDetailsComponent,
    canActivate: [AuthGuard],
  },
  { path: 'sidenav', component: SidenavComponent },
  { path: 'login', component: LoginComponent, pathMatch: 'full' },
  { path: 'signup', component: SignupComponent, pathMatch: 'full' },
  {
    path: 'forgetpassword',
    component: ForgetpasswordComponent,
    pathMatch: 'full',
  },
  {
    path: 'resetpassword',
    component: ResetpasswordComponent,
    pathMatch: 'full',
  },
  {
    path: 'userprofile/:user_id',
    component: UserprofileComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'issue-book-details',
    component: IssueBookDetailsComponent,
    canActivate: [AdminguardGuard],
  },
  {
    path: 'add-book-details',
    component: AddBookDetailsComponent,
    canActivate: [AdminguardGuard],
  },
  {
    path: 'changepassword/:user_id',
    component: ChangepasswordComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'book-reqs',
    component: BookRequestsComponent,
    canActivate: [AdminguardGuard],
  },
  {
    path: 'reqs-for-ext',
    component: ReqForExtComponent,
    canActivate: [AdminguardGuard],
  },
  { path: '**', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
