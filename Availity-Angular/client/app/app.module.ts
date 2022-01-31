import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {SharedModule} from "./shared/shared.module";
import {HttpClientModule} from "@angular/common/http";
import { LoginContainerComponent } from './login/login-container/login-container.component';
import { UserRegistrationComponent } from './login/user-registration/user-registration.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginContainerComponent,
    UserRegistrationComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
