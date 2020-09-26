import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { EcommerceService } from 'src/app/services/ecommerce.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  transactions : any;
  transactionNumber: '';
  invoiceNumber: '';
  paymentAmount: 0;
  fullName: '';
  transactionDate: '';
  statePayment:'';
  ecommerce:'';


  constructor(private ecommerceService: EcommerceService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll():void {
    this.ecommerceService.getAll()
      .subscribe(
        response => {
          localStorage.setItem('Transacciones', JSON.stringify({data: response}));
          console.log(response)

          this.transactions = response;

          const transactionLS = JSON.parse(localStorage.getItem('Transacciones'))

          console.log(transactionLS)
        },
          error => {console.log(error)}
      )
   }

       /*const transaccionLS = localStorage.getItem('Transacciones')
    const data = {
      transactionNumber: this.transaccion.transactionNumber,
      paymentAmount: this.transaccion.paymentAmount,
      commissionAmount: this.transaccion.commissionAmount,
      invoiceNumber: this.transaccion.invoiceNumber,
      transactionDate: this.transaccion.transactionDate,
      statePayment: this.transaccion.statePayment,
    };*/

}
