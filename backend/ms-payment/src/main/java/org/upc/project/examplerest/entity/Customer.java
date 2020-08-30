package org.upc.project.examplerest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "account_number")
    private String accountNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    @DateTimeFormat(pattern = "MM/yy")
    private Date expirationDate;

    @Column(name = "identification_code")
    private int identificationCode;

}
