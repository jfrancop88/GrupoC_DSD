package org.upc.project.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.project.payment.beans.CustomerDTO;
import org.upc.project.payment.beans.PaymentDTO;
import org.upc.project.payment.entity.*;
import org.upc.project.payment.message.PaymentPublisher;
import org.upc.project.payment.repository.BankRepository;
import org.upc.project.payment.repository.EcommerceRepository;
import org.upc.project.payment.repository.PaymentRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PaymentService implements GenericService<PaymentDTO> {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EcommerceRepository ecommerceRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private PaymentPublisher paymentPublisher;

    private final SimpleDateFormat format = new SimpleDateFormat("MM/yy");

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public List<PaymentDTO> findAll() {
        List<Payment> payments = (List<Payment>) paymentRepository.findAll();
        List<PaymentDTO> dtos = new ArrayList<>();

        payments.forEach(payment -> {
            dtos.add(PaymentDTO.builder()
                    .transactionNumber(payment.getTransactionNumber())
                    .invoiceNumber(payment.getInvoiceNumber())
                    .paymentAmount(payment.getPaymentAmount())
                    .commissionAmount(payment.getCommissionAmount())
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
                    .build());
        });

        return dtos;
    }

    @Override
    public Optional<PaymentDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) throws Exception {

        String bingNumber = paymentDTO.getCustomer().getAccountNumber().substring(0, 6);
//        if (paymentDTO.getCustomer().getAccountNumber().contains(bingNumber)) {
//            throw new Exception("Bin incorrecto");
//
//        }
        Bank bank = bankRepository.findByBinNumber(bingNumber);

        if (validateCustomerCard(paymentDTO.getCustomer(), bank)) {
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
                .bank(bank)
                .build();

        BigDecimal commissionAmount = paymentDTO.getPaymentAmount().multiply(new BigDecimal(ecommerce.getCommissionPercentage()))
                .divide(new BigDecimal(100));

        Payment payment = Payment.builder()
                .paymentAmount(paymentDTO.getPaymentAmount())
                .commissionAmount(commissionAmount)
                .invoiceNumber(paymentDTO.getInvoiceNumber())
                .verificationNumber(paymentDTO.getVerificationNumber())
                .statePayment(StatePayment.IN_PROCESS)
                .quota(paymentDTO.getQuota())
                .updateUser("ADMIN")
                .customer(customer)
                .ecommerce(ecommerce)
                .build();


        Payment paymentCreate = paymentRepository.save(payment);

        paymentDTO.setTransactionDate(paymentCreate.getPaymentDate());
        paymentDTO.setTransactionNumber(paymentCreate.getTransactionNumber());
        paymentDTO.setEcommerce(paymentCreate.getEcommerce().getEcommerceCode());

        String json = MAPPER.writeValueAsString(paymentDTO);
        paymentPublisher.sendMessage(json);

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


    private boolean validateCustomerCard(CustomerDTO customer, Bank bank) throws ParseException {
        Date expirationDate = format.parse(customer.getExpirationDate());
        Date endDate = Date.from(LocalDate.now().plusYears(5).atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant());

        return !(customer.getAccountNumber().contains(bank.getBinNumber()) &&
                customer.getAccountNumber().matches("^[0-9]{16}$") &&
                customer.getAccountNumber().length() == 16 &&
                expirationDate.before(endDate) &&
                String.valueOf(customer.getIdentificationCode()).length() == 3);

    }
}
