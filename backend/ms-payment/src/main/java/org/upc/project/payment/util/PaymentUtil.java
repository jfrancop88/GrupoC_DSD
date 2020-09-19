package org.upc.project.payment.util;

import org.upc.project.payment.beans.CustomerDTO;
import org.upc.project.payment.beans.PaymentDTO;
import org.upc.project.payment.entity.Bank;
import org.upc.project.payment.entity.Payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class PaymentUtil {
    public static final SimpleDateFormat format = new SimpleDateFormat("MM/yy");

    /**
     * Validacion de Tarjeta del Cliente
     */
    public static boolean validateCustomerCard(CustomerDTO customer, Bank bank) throws ParseException {
        Date expirationDate = format.parse(customer.getExpirationDate());
        Date endDate = Date.from(LocalDate.now().plusYears(5).atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant());

        return !(customer.getAccountNumber().contains(bank.getBinNumber()) &&
                customer.getAccountNumber().matches("^[0-9]{16}$") &&
                customer.getAccountNumber().length() == 16 &&
                expirationDate.before(endDate) &&
                String.valueOf(customer.getIdentificationCode()).length() == 3);

    }

    public static PaymentDTO build(Payment payment){
        PaymentDTO paymentDTO = PaymentDTO.builder()
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
                .build();
        return paymentDTO;
    }
}
