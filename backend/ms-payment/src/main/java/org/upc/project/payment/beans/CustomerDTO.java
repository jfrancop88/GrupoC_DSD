package org.upc.project.payment.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@JsonInclude(JsonInclude.Include.CUSTOM)
@Data
public class CustomerDTO {

    private String fullName;

    private String email;

    private String accountNumber;

    private String telephoneNumber;

    private String documentNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    public String expirationDate;

    private int identificationCode;

}
