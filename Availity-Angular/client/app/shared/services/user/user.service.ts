import { Injectable } from '@angular/core';
import {HttpService} from "../http/http.service";
import {UserModel} from "../../models/UserModel";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpService) { }

  getUser() {
    return new Promise(((resolve, reject) => {

      //this is where I would hook up to the http service
    //   this.http.getUser().subscribe({
    //     next: (user: any) => {
    //       resolve(user);
    //     },
    //     error: (error: any) => {
    //       reject(error);
    //     }
    //   })

      resolve(null);
    }))
  }

  registerUser(userRegistrationForm: FormGroup) {
    return new Promise((resolve, reject) => {
      resolve({userId: "UserToken"});
      //this is where I would hook up to the http service
      //   this.http.registerUser().subscribe({
      //     next: (user: any) => {
      //       resolve(user);
      //     },
      //     error: (error: any) => {
      //       reject(error);
      //     }
      //   })
    })
  }
}
