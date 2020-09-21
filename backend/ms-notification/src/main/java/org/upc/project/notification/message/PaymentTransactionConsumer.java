package org.upc.project.notification.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.upc.project.notification.beans.*;
import org.upc.project.notification.entity.PaymentNotification;
import org.upc.project.notification.repository.PaymentNotificationRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    private RestTemplate restTemplate;

    @Autowired
    private PaymentNotificationRepository paymentNotificationRepository;

    @JmsListener(destination = "${jms.queue.consumer}")
    public void receiveMessage(String jsonPayload) {

        try {

            PaymentDTO json = objectMapper.readValue(jsonPayload, PaymentDTO.class);

            log.info("Received <" + json.toString() + ">");
            String rawJson = objectMapper.writeValueAsString(json);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", apiToken);

            SendgridRequest sendgridRequest = new SendgridRequest();

            Email emailSubject = new Email();
            emailSubject.setEmail("daliafer.sp@gmail.com");

            DataEmail dataEmail = DataEmail.builder()
                    .transactionId(json.getTransactionNumber())
                    .amount(json.getPaymentAmount().toString())
                    .description("Compras " + json.getEcommerce())
                    .ecommerce(json.getEcommerce())
                    .transactionDate(DATE_FORMAT.format(json.getTransactionDate()))
                    .fullName(json.getCustomer().getFullName())
                    .build();

            DynamicTemplate dynamicTemplate = new DynamicTemplate();
            dynamicTemplate.setDataEmail(dataEmail);
            dynamicTemplate.setSubject("epay-constancia");
            Email email = new Email();
            email.setEmail("alvarodaniel2808@gmail.com");
            List<Email> emails = Collections.singletonList(email);

            dynamicTemplate.setTo(emails);

            List<DynamicTemplate> dynamicTemplates = Collections.singletonList(dynamicTemplate);
            sendgridRequest.setPersonalizations(dynamicTemplates);
            sendgridRequest.setFrom(emailSubject);
            sendgridRequest.setTemplate("d-9377d54791114dc5a222c7619b55277b");

            HttpEntity<SendgridRequest> request = new HttpEntity<>(sendgridRequest, headers);

            ResponseEntity<String> responseEmail = restTemplate.postForEntity(url, request, String.class);

            PaymentNotification paymentNotification = new PaymentNotification();
            paymentNotification.setEcommerceCode(json.getEcommerce());
            paymentNotification.setRawJson(rawJson);
            paymentNotification.setTransactionId(json.getTransactionNumber());
            paymentNotification.setIsSendEmail(true);

            log.info("Create Notification Send transactionId {}", json.getTransactionNumber());
            paymentNotificationRepository.save(paymentNotification);
        } catch (JsonProcessingException ex) {
            PaymentNotification paymentNotification = new PaymentNotification();
            //paymentNotification.setEcommerceCode(json.getEcommerce());
            paymentNotification.setRawJson(jsonPayload);
            //paymentNotification.setTransactionId(json.getTransactionNumber());
            paymentNotification.setIsSendEmail(false);
            paymentNotification.setNotificationUpdated(new Date());
            paymentNotificationRepository.save(paymentNotification);
        }
    }
}
