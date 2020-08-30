package org.upc.project.examplerest.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.upc.project.examplerest.entity.StatePayment;

import java.math.BigDecimal;
import java.util.Date;


@Builder
@Data
@JsonInclude(JsonInclude.Include.CUSTOM)
public class PaymentDTO {

    private String transactionNumber;

    private BigDecimal paymentAmount;

    private BigDecimal taxAmount;

    private String invoiceNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;

    private StatePayment statePayment;

    private String updateUser;

    private String verificationNumber;

    private int quota;

    private String ecommerce;

    private String ecommerceName;

    private CustomerDTO customer;
}
