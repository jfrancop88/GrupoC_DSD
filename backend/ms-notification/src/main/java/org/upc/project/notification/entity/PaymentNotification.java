package org.upc.project.notification.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "payment_notification")
public class PaymentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String transactionId;

    @Column
    @CreationTimestamp
    private Date notificationDate;

    @Column
    @UpdateTimestamp
    private Date notificationUpdated;

    @Column(columnDefinition = "text")
    private String rawJson;

    @Column
    private Boolean isSendEmail;

    @Column
    private String ecommerceCode;
}
