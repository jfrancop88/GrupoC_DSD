import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ComercioComponent } from './comercio/comercio.component';
import { CheckoutComponent } from './checkout/checkout.component';

// sign component
import { SigninComponent } from './auth/signin.component';
import { SignupComponent } from './auth/signup.component';
import { ForgotPasswordComponent } from './auth/forgotpassword.component';
import { ResetPasswordComponent } from './auth/resetpassword.component';


// service
import { RestDataSource } from './service/rest.datasource';
import { AuthService } from './service/auth.service';
import { Base64Service } from './service/base64.service';
import { AuthGuard } from './guard/auth.guard';
import { ProviderARepository, ProviderHRepository, Activas,
         Archivadas, Busqueda } from './service/model.repository';
import { DashboardComponent } from './dashboard/dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    ComercioComponent,
    CheckoutComponent,
    SigninComponent,
    SignupComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpModule
  ],
  providers: [
    RestDataSource,
    AuthService,
    Base64Service,
    ProviderARepository,
    ProviderHRepository,
    Activas,
    Archivadas,
    Busqueda,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
