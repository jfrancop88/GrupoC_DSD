package org.upc.project.notification.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.upc.project.notification.beans.PaymentDTO;
import org.upc.project.notification.entity.PaymentNotification;
import org.upc.project.notification.repository.PaymentNotificationRepository;

@Slf4j
@Component
public class PaymentTransactionConsumer {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    @Autowired
    private PaymentNotificationRepository paymentNotificationRepository;

    @JmsListener(destination = "${jms.queue.consumer}")
    public void receiveMessage(String jsonPayload) throws JsonProcessingException {

        PaymentDTO json = objectMapper.readValue(jsonPayload, PaymentDTO.class);
        log.info("Received <" + json.toString() + ">");

        String rawJson = objectMapper.writeValueAsString(json);

        PaymentNotification paymentNotification = new PaymentNotification();
        paymentNotification.setEcommerceCode(json.getEcommerce());
        paymentNotification.setRawJson(rawJson);
        paymentNotification.setTransactionId(json.getTransactionNumber());
        paymentNotification.setIsSendEmail(true);

        log.info("Create Notification Send transactionId {}", json.getTransactionNumber());
        paymentNotificationRepository.save(paymentNotification);
    }
}
