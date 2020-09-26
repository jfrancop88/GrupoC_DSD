import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  data = {
    code: '',
    name: '',
    percentage: 2,
    percentageT: '2.00 %',
    accountNumber: '',
  }

  bank = '';

  errorMessage: string;
  submitted = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  register():void {
    this.errorMessage = null;
    const comercio = {
      code: this.data.code,
      name: this.data.name,
      percentage: this.data.percentage,
      accountNumber: this.data.accountNumber
    };

    this.authService.registerCommerce(comercio)
    .subscribe(
      response => {
        console.log(response);
        localStorage.setItem('Comercio', JSON.stringify({code: this.data.code, name: this.data.name}));
        this.router.navigateByUrl('/register');
        this.submitted = true;
      },
      error => {
        console.log(error);
        this.errorMessage = "No se ha podido regitrar el comercio, intente nuevamente en unos minutos";
      }
    )
  }


}
