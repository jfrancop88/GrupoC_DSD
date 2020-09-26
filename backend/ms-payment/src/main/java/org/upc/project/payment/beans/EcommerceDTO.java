package org.upc.project.payment.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@JsonInclude(JsonInclude.Include.CUSTOM)
@Data
public class EcommerceDTO {
    private String name;
    private String code;
    private String accountNumber;
    private int percentage;
}
