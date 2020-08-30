package org.upc.project.examplerest.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.upc.project.examplerest.entity.Customer;
import org.upc.project.examplerest.entity.Ecommerce;
import org.upc.project.examplerest.entity.Payment;
import org.upc.project.examplerest.entity.StatePayment;
import org.upc.project.examplerest.repository.PaymentRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Component
public class InitializerData implements CommandLineRunner {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {

        Customer customer = Customer.builder()
                .accountNumber("455912313334111")
                .email("alvaro92a18@gmail.com")
                .expirationDate(new SimpleDateFormat("MM/yy").parse("12/23"))
                .fullName("Alvro Aguinaga")
                .identificationCode(912)
                .build();

        Ecommerce ecommerce = Ecommerce.builder()
                .ecommerceCode("SAGA")
                .name("SAGA FALABELLA")
                .enabled(true)
                .build();

        Payment payment = Payment.builder()
                .invoiceNumber("20470929031")
                .paymentAmount(BigDecimal.valueOf(459.0))
                .verificationNumber("CAE12a")
                .statePayment(StatePayment.IN_PROCESS)
                .taxAmount(BigDecimal.valueOf(4.59))
                .updateUser("ADMIN")
                .customer(customer)
                .ecommerce(ecommerce)
                .quota(2)
                .build();

        paymentRepository.save(payment);
    }
}
