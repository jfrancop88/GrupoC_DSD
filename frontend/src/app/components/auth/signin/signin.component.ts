import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  data = {
    username: '',
    password: '',
  }

  remember = false;
  errorMessage: String;
  submitted = false;
  
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  signin():void {
    this.errorMessage = null;
    const login = {
      username: this.data.username,
      password: this.data.password
    };

    this.authService.signin(login)
    .subscribe(
      response => {
        if (!response.error){
          console.log(response);
          localStorage.setItem('Usuario', JSON.stringify({token: response.token, username: response.username, user_id: response.user_id, ecommerce_id: response.ecommerce_id}));
          this.submitted = true;}
        else
        { this.errorMessage = response.error}

      },
      
      error => {console.log(error),
      this.errorMessage = error.message}
    )
  }


}
