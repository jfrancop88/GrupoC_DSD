import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//Valida la sesión, se usa como middleware
import { AuthGuard } from './guard/auth.guard';

// sign component
import { SigninComponent } from './auth/signin.component';
import { SignupComponent } from './auth/signup.component';
import { ForgotPasswordComponent } from './auth/forgotpassword.component';
import { ResetPasswordComponent } from './auth/resetpassword.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [

  { path: 'signin', component: SigninComponent},
  { path: 'signup', component: SignupComponent},
  { path: 'resetpassword', component: ResetPasswordComponent},
  { path: 'forgotpassword', component: ForgotPasswordComponent},
  
  { path: 'dashboard', component: DashboardComponent},

  { path: 'checkout', component: CheckoutComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
