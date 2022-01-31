import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserService} from "../../shared/services/user/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
// @ts-ignore
import {v4 as uuidv4} from 'uuid';
import {UserModel} from "../../shared/models/UserModel";
import {augmentAppWithServiceWorker} from "@angular-devkit/build-angular/src/utils/service-worker";

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.sass']
})
export class UserRegistrationComponent implements OnInit {

  userRegistrationForm!: FormGroup;
  @Input() user!: UserModel;
  @Output() loggedInToken = new EventEmitter<{}>();

  emailRegex = '(?:[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*' +
    '|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@' +
    '(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\' +
    '[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|' +
    '[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a' +
    '\x53-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\\])'

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {

    //this is what I would recommend to use as the npinumber.
    //I would also do this on the backend so the number isn't being transferred over the wire
    // let uuidNpiNumber = uuidv4();
    let uuidNpiNumber = Math.random()+Date.now()+'';
    uuidNpiNumber = uuidNpiNumber.replace('.', '')
    uuidNpiNumber = uuidNpiNumber.slice(uuidNpiNumber.length - 10)

    this.userRegistrationForm = new FormGroup({
      firstName: new FormControl(
        '',
        Validators.required
      ),
      lastName: new FormControl(
        '',
        Validators.required
      ),
      npiNumber: new FormControl(
        uuidNpiNumber
      ),
      businessAddress: new FormControl(
        '',
        Validators.required
      ),
      telephoneNumber: new FormControl(
        '',
        Validators.compose([
          Validators.required,
          Validators.pattern('^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$')
        ])
      ),
      email: new FormControl(
        '',
        Validators.compose([
          Validators.required,
          Validators.pattern(this.emailRegex)
        ])
      )
    })
  }

  register() {
    //double check that this is valid
    if(this.userRegistrationForm.valid){
      //this would register the user
      this.userService.registerUser(this.userRegistrationForm)
        .then((result: any)=> {
          this.loggedIn(result);
        });
    } else {
      //it is not valid so don't do anything
    }
  }

   loggedIn(result: any){
    this.loggedInToken.emit(result);
  }

}
