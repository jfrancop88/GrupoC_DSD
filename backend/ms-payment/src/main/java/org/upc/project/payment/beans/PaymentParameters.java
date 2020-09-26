package org.upc.project.payment.beans;

import lombok.Data;

@Data
public class PaymentParameters {

    private String name;
    private String transactionNumber;
    private String date;
}
