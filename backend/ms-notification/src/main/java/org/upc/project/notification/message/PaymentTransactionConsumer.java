package org.upc.project.notification.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.upc.project.notification.beans.PaymentDTO;
import org.upc.project.notification.entity.PaymentNotification;
import org.upc.project.notification.repository.PaymentNotificationRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class PaymentTransactionConsumer {

    private static final ObjectMapper objectMapper;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    static {
        objectMapper = new ObjectMapper();
    }

    private static final String url = "https://api.sendgrid.com/v3/mail/send";
    private static final String apiToken = "Bearer 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";

    @Autowired
    private SendgridApi sendgridApi;

    @Autowired
    private PaymentNotificationRepository paymentNotificationRepository;

    @JmsListener(destination = "${jms.queue.consumer}")
    public void receiveMessage(String jsonPayload) {
        PaymentDTO json = null;
        try {
            json = objectMapper.readValue(jsonPayload, PaymentDTO.class);

            log.info("Received <" + json.toString() + ">");
            String rawJson = objectMapper.writeValueAsString(json);

            sendgridApi.sendMessageEmail(json);

            PaymentNotification paymentNotification = new PaymentNotification();
            paymentNotification.setEcommerceCode(json.getEcommerce());
            paymentNotification.setRawJson(rawJson);
            paymentNotification.setTransactionId(json.getTransactionNumber());
            paymentNotification.setIsSendEmail(true);

            log.info("Create Notification Send transactionId {}", json.getTransactionNumber());
            paymentNotificationRepository.save(paymentNotification);
        } catch (Exception ex) {
            PaymentNotification paymentNotification = new PaymentNotification();
            paymentNotification.setEcommerceCode(json.getEcommerce());
            paymentNotification.setRawJson(jsonPayload);
            paymentNotification.setTransactionId(json.getTransactionNumber());
            paymentNotification.setIsSendEmail(false);
            paymentNotification.setNotificationUpdated(new Date());
            paymentNotificationRepository.save(paymentNotification);
        }
    }
}
