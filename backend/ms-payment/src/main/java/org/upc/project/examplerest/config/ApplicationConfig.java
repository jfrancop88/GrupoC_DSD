package org.upc.project.examplerest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.upc.project.examplerest.repository.BankRepository;
import org.upc.project.examplerest.repository.CustomerRepository;
import org.upc.project.examplerest.repository.EcommerceRepository;
import org.upc.project.examplerest.repository.PaymentRepository;

@EnableJpaRepositories(
        basePackageClasses = {PaymentRepository.class, CustomerRepository.class,
                EcommerceRepository.class, BankRepository.class})
@Configuration
public class ApplicationConfig {
}
