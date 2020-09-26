package org.upc.project.notification.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.upc.project.notification.beans.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SendgridApi {


    @Value("${sendgrid.url}")
    private String url;

    @Value("${sendgrid.api-key}")
    private String apiToken;

    @Value("${sendgrid.email}")
    private String email;

    @Value("${sendgrid.template}")
    private String template;

    @Autowired
    private RestTemplate restTemplate;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    private static final String SUBJECT = "Hello, World!";

    public void sendMessageEmail(PaymentDTO paymentDTO) throws Exception {
        log.info("Send Email Api Sendgrid");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", apiToken);

        SendgridRequest sendgridRequest = new SendgridRequest();

        Email emailSubject = new Email();
        emailSubject.setEmail(email);

        DataEmail dataEmail = DataEmail.builder()
                .transactionId(paymentDTO.getTransactionNumber())
                .amount(paymentDTO.getPaymentAmount().toString())
                .description("Compras " + paymentDTO.getEcommerce())
                .ecommerce(paymentDTO.getEcommerce())
                .transactionDate(DATE_FORMAT.format(paymentDTO.getTransactionDate()))
                .fullName(paymentDTO.getCustomer().getFullName())
                .build();

        DynamicTemplate dynamicTemplate = new DynamicTemplate();
        dynamicTemplate.setDataEmail(dataEmail);
        dynamicTemplate.setSubject(SUBJECT);
        Email email = new Email();
        email.setEmail(paymentDTO.getCustomer().getEmail());
        List<Email> emails = Collections.singletonList(email);

        dynamicTemplate.setTo(emails);

        List<DynamicTemplate> dynamicTemplates = Collections.singletonList(dynamicTemplate);
        sendgridRequest.setPersonalizations(dynamicTemplates);
        sendgridRequest.setFrom(emailSubject);
        sendgridRequest.setTemplate(template);
        sendgridRequest.setSubject(SUBJECT);

        HttpEntity<SendgridRequest> request = new HttpEntity<>(sendgridRequest, headers);

        ResponseEntity<String> responseEmail = restTemplate.postForEntity(url, request, String.class);
        if (responseEmail.getStatusCode() != HttpStatus.ACCEPTED) {
            throw new Exception("Error Service Sendgrid");
        }
        log.info("Finish Email Api Sengrid");
    }
}
