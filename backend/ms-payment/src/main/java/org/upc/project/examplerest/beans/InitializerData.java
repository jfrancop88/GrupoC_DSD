package org.upc.project.examplerest.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.upc.project.examplerest.entity.*;
import org.upc.project.examplerest.repository.PaymentRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

//@Component
public class InitializerData implements CommandLineRunner {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {

        Bank bank = Bank.builder()
                .bankCode("1234124")
                .bankName("BCP")
                .binNumber("455788")
                .build();

        Customer customer = Customer.builder()
                .accountNumber("4557883133341115")
                .documentNumber("5646899")
                .email("alvaro92a18@gmail.com")
                .expirationDate(new SimpleDateFormat("MM/yy").parse("12/23"))
                .fullName("Alvro Aguinaga")
                .identificationCode(912)
                .bank(bank)
                .build();

        Ecommerce ecommerce = Ecommerce.builder()
                .ecommerceCode("SAGA")
                .name("SAGA FALABELLA")
                .commissionPercentage(2)
                .enabled(true)
                .build();

        Payment payment = Payment.builder()
                .invoiceNumber("20470929031")
                .paymentAmount(BigDecimal.valueOf(459.0))
                .verificationNumber("CAE12a")
                .statePayment(StatePayment.IN_PROCESS)
                .commissionAmount(BigDecimal.valueOf(4.59))
                .updateUser("ADMIN")
                .customer(customer)
                .ecommerce(ecommerce)
                .quota(2)
                .build();

        paymentRepository.save(payment);
    }
}
