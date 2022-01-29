import { Component, OnInit } from '@angular/core';
import {interval, retry, share, startWith, Subscription, switchMap} from "rxjs";
import {UserService} from "../../shared/services/user/user.service";
import {UserModel} from "../../shared/models/UserModel";

@Component({
  selector: 'app-login-container',
  templateUrl: './login-container.component.html',
  styleUrls: ['./login-container.component.sass']
})
export class LoginContainerComponent implements OnInit {

  scheduler!: Subscription;
  loading = false;
  user!: UserModel;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.scheduler = interval(5000)
      .pipe(
        startWith(this.loading = true),
        switchMap(()=> this.userService.getUser()),
        retry(),
        share()
      ).subscribe({
        next: (user: any) => {
          this.user = user;
          },
        error: (err) => {
          console.log(err);
        }
      })
  }

}
