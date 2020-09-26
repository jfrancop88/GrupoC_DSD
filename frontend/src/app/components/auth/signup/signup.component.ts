import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  data = {
    username: '',
    password: '',
    email: '',
    ecommerce_id: '1',
  }

  remember = false;
  errorMessage: String;
  submitted = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    const local = localStorage.getItem('Comercio');
        if (local) {
          const { code, name } = JSON.parse(local);
          this.data.ecommerce_id = code + ' - ' + name;
        }
  }

  register():void {
    this.errorMessage = null;
    const registro = {
      username: this.data.username,
      password: this.data.password,
      email: this.data.email,
      ecommerce_id: this.data.ecommerce_id
    };

    this.authService.register(registro)
    .subscribe(
      response => {
        console.log(response);
        this.submitted = true;
      },
      error => {console.log(error)
      this.errorMessage = "No se ha podido regitrar el comercio, intente nuevamente en unos minutos";}
    )
  }


}
