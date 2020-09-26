import { Component, OnInit } from '@angular/core';
import { CheckoutService } from 'src/app/services/checkout.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent implements OnInit {

  check = {
    paymentAmount: '100.00',
    invoiceNumber: 'PruebaFront',
    verificationNumber: '457892',
    quota: 0,
    customer: {
      fullName: '',
      email: '',
      telephoneNumber: '',
      documentNumber: '',
      accountNumber: '',
      expirationDateM: '',
      expirationDateY: '',
      identificationCode: 0
    }
  };

  submitted = false;

  constructor(private checkoutService: CheckoutService) { }

  ngOnInit(): void {
  }

  paymentSave():void {
    const data = {
      paymentAmount: this.check.paymentAmount,
      invoiceNumber: this.check.invoiceNumber,
      verificationNumber: this.check.verificationNumber,
      quota: this.check.quota,
      customer: {
        fullName: this.check.customer.fullName,
        email: this.check.customer.email,
        telephoneNumber: this.check.customer.telephoneNumber,
        documentNumber: this.check.customer.documentNumber,
        accountNumber: this.check.customer.accountNumber,
        expirationDate: this.check.customer.expirationDateM + '/' + this.check.customer.expirationDateY,
        identificationCode: this.check.customer.identificationCode
      }
    };

    this.checkoutService.paymentSave(data)
    .subscribe(
      response => {
        console.log(response);
        this.submitted = true;
      },
      error => console.log(error)
    )
  }


}
