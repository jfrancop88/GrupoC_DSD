package org.upc.project.payment.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentPublisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value(value = "${jms.queue.publisher}")
    private String queueProducer;

    public void sendMessage(String paymentDTO) {
        jmsTemplate.convertAndSend(queueProducer, paymentDTO);
    }

}
