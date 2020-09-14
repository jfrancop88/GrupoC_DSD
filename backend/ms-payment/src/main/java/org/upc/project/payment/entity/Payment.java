package org.upc.project.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "payment_transaction")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "transaction_number")
    private final String transactionNumber = UUID.randomUUID().toString();

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "commission_amount")
    private BigDecimal commissionAmount;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @CreationTimestamp
    @Column(name = "payment_date")
    private Date paymentDate;

    @UpdateTimestamp
    @Column(name = "process_date")
    private Date processDate;

    @UpdateTimestamp
    @Column(name = "rejected_date")
    private Date rejectedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_payment")
    private StatePayment statePayment = StatePayment.IN_PROCESS;

    @Column(name = "verification_number")
    private String verificationNumber;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "quota")
    private int quota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ecommerce_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ecommerce ecommerce;

}
