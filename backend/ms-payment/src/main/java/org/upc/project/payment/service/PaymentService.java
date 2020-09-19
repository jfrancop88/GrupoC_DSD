package org.upc.project.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.upc.project.payment.beans.CustomerDTO;
import org.upc.project.payment.beans.PaymentDTO;
import org.upc.project.payment.beans.PaymentParameters;
import org.upc.project.payment.entity.*;
import org.upc.project.payment.message.PaymentPublisher;
import org.upc.project.payment.repository.BankRepository;
import org.upc.project.payment.repository.CustomerRepository;
import org.upc.project.payment.repository.EcommerceRepository;
import org.upc.project.payment.repository.PaymentRepository;
import org.upc.project.payment.util.PaymentUtil;

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

    @Autowired
    private CustomerRepository customerRepository;

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public List<PaymentDTO> findAll() {
        List<Payment> payments = (List<Payment>) paymentRepository.findAll();
        List<PaymentDTO> dtos = new ArrayList<>();
        payments.forEach(payment -> dtos.add(PaymentUtil.build(payment)));
        return dtos;
    }

    @Override
    public PaymentDTO findById(String id) {
        Payment payment = paymentRepository.findByTransactionNumber(id);
        return PaymentUtil.build(payment);
    }

    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) throws Exception {

        String bingNumber = paymentDTO.getCustomer().getAccountNumber().substring(0, 6);
//        if (paymentDTO.getCustomer().getAccountNumber().contains(bingNumber)) {
//            throw new Exception("Bin incorrecto");
//
//        }
        Bank bank = bankRepository.findByBinNumber(bingNumber);

        if (PaymentUtil.validateCustomerCard(paymentDTO.getCustomer(), bank)) {
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
                .expirationDate(PaymentUtil.format.parse(paymentDTO.getCustomer().getExpirationDate()))
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

    public List<PaymentDTO> findAllByParameters(PaymentParameters parameters) {
        List<PaymentDTO> dtos = new ArrayList<>();

        List<Payment> payments;
        if (!StringUtils.isEmpty(parameters.getName())) {
            List<Customer> customers = customerRepository.findByFullNameStartsWith(parameters.getName());
            customers.forEach(customer -> {
                List<Payment> payments2 = paymentRepository.findAllByCustomer(customer);
                payments2.forEach(payment -> dtos.add(PaymentUtil.build(payment)));
            });
        } else if (!StringUtils.isEmpty(parameters.getTransactionNumber())) {
            payments = paymentRepository.findAllByTransactionNumberOrPaymentDate(parameters.getTransactionNumber(), null);
            payments.forEach(payment -> dtos.add(PaymentUtil.build(payment)));
        } else {
            payments = paymentRepository.findAllByTransactionNumberOrPaymentDate(null, null);
            payments.forEach(payment -> dtos.add(PaymentUtil.build(payment)));
        }
        return dtos;
    }
}
