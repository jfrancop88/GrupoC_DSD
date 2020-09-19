package org.upc.project.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upc.project.payment.beans.PaymentDTO;
import org.upc.project.payment.beans.PaymentParameters;
import org.upc.project.payment.service.PaymentService;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/payment")
    public ResponseEntity<?> getListPayments() {

        return ResponseEntity.ok(paymentService.findAll());
    }

    @PostMapping(value = "/payment")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO request) throws Exception {

        return ResponseEntity.ok(paymentService.save(request));
    }

    @GetMapping(value = "/payment/{transactionId}")
    public ResponseEntity<?> getPaymentTransaction(@PathVariable("transactionId") String id) throws Exception {

        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping(value = "/payment/parameters")
    public ResponseEntity<?> listPaymentParameters(@RequestBody PaymentParameters parameters) throws Exception {

        return ResponseEntity.ok(paymentService.findAllByParameters(parameters));
    }
}
