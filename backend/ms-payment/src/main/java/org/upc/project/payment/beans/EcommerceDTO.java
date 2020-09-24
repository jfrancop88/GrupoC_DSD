package org.upc.project.payment.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@JsonInclude(JsonInclude.Include.CUSTOM)
@Data
public class EcommerceDTO {
    private String name;
    private String ruc;
    private String accountNumber;
}
