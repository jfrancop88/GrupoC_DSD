package org.upc.project.examplerest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.project.examplerest.beans.CustomerDTO;
import org.upc.project.examplerest.beans.PaymentDTO;
import org.upc.project.examplerest.entity.Customer;
import org.upc.project.examplerest.entity.Ecommerce;
import org.upc.project.examplerest.entity.Payment;
import org.upc.project.examplerest.entity.StatePayment;
import org.upc.project.examplerest.repository.EcommerceRepository;
import org.upc.project.examplerest.repository.PaymentRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PaymentService implements GenericService<PaymentDTO> {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EcommerceRepository ecommerceRepository;

    private final SimpleDateFormat format = new SimpleDateFormat("MM/yy");

    @Override
    public List<PaymentDTO> findAll() {
        List<Payment> payments = (List<Payment>) paymentRepository.findAll();
        List<PaymentDTO> dtos = new ArrayList<>();

        payments.forEach(payment -> {
            dtos.add(
                    PaymentDTO.builder()
                            .transactionNumber(payment.getTransactionNumber())
                            .invoiceNumber(payment.getInvoiceNumber())
                            .paymentAmount(payment.getPaymentAmount())
                            .taxAmount(payment.getTaxAmount())
                            .statePayment(payment.getStatePayment())
                            .updateUser(payment.getUpdateUser())
                            .transactionDate(payment.getPaymentDate())
                            .ecommerce(payment.getEcommerce().getEcommerceCode())
                            .ecommerceName(payment.getEcommerce().getName())
                            .customer(CustomerDTO.builder()
                                    .accountNumber(payment.getCustomer().getAccountNumber())
                                    .email(payment.getCustomer().getEmail())
                                    .fullName(payment.getCustomer().getFullName())
                                    .expirationDate(format.format(payment.getCustomer().getExpirationDate()))
                                    .identificationCode(payment.getCustomer().getIdentificationCode())
                                    .build())
                            .build()
            );
        });

        return dtos;
    }

    @Override
    public Optional<PaymentDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) throws Exception {

        if (!paymentDTO.getCustomer().getAccountNumber().contains("4559")) {
            throw new Exception("Tarjeta Invalida");
        }

        Ecommerce ecommerce = ecommerceRepository.findByEcommerceCode("SAGA");

        Customer customer = Customer
                .builder()
                .fullName(paymentDTO.getCustomer().getFullName())
                .email(paymentDTO.getCustomer().getEmail())
                .telephoneNumber(paymentDTO.getCustomer().getTelephoneNumber())
                .documentNumber(paymentDTO.getCustomer().getDocumentNumber())
                .accountNumber(paymentDTO.getCustomer().getAccountNumber())
                .expirationDate(format.parse(paymentDTO.getCustomer().getExpirationDate()))
                .identificationCode(paymentDTO.getCustomer().getIdentificationCode())
                .build();

        Payment payment = Payment.builder()
                .paymentAmount(paymentDTO.getPaymentAmount())
                .taxAmount(paymentDTO.getTaxAmount())
                .invoiceNumber(paymentDTO.getInvoiceNumber())
                .verificationNumber(paymentDTO.getVerificationNumber())
                .statePayment(StatePayment.IN_PROCESS)
                .quota(paymentDTO.getQuota())
                .updateUser("ADMIN")
                .customer(customer)
                .ecommerce(ecommerce)
                .build();


        Payment paymentCreate = paymentRepository.save(payment);

        return PaymentDTO.builder()
                .transactionNumber(paymentCreate.getTransactionNumber())
                .transactionDate(paymentCreate.getPaymentDate())
                .build();
    }

    @Override
    public PaymentDTO update(PaymentDTO paymentDTO) {
        return null;
    }

    @Override
    public PaymentDTO delete(Long id) {
        return null;
    }

}
