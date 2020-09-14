package org.upc.project.payment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.upc.project.payment.repository.BankRepository;
import org.upc.project.payment.repository.CustomerRepository;
import org.upc.project.payment.repository.EcommerceRepository;
import org.upc.project.payment.repository.PaymentRepository;

@EnableJpaRepositories(
        basePackageClasses = {PaymentRepository.class, CustomerRepository.class,
                EcommerceRepository.class, BankRepository.class})
@Configuration
public class ApplicationConfig {
}
