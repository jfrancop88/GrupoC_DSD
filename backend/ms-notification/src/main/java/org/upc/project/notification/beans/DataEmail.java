package org.upc.project.notification.beans;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataEmail {

    private String transactionId;
    private String ecommerce;
    private String description;
    private String amount;
    private String transactionDate;
    private String fullName;
}
