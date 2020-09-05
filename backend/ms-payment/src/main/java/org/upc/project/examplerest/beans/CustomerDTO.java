package org.upc.project.examplerest.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
