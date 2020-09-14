package org.upc.project.notification.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.CUSTOM)
@Data
public class CustomerDTO implements Serializable {

    private String fullName;

    private String email;

    private String accountNumber;

    private String telephoneNumber;

    private String documentNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    public String expirationDate;

    private int identificationCode;

}
