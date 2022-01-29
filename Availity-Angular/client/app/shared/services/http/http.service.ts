import { Injectable } from '@angular/core';
import {HttpClient, HttpEventType} from "@angular/common/http";
import {UserModel} from "../../models/UserModel";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }


  getUser() {
    return Promise.resolve();
  }

  registerUser(params: UserModel){
    return params;
  }
}
