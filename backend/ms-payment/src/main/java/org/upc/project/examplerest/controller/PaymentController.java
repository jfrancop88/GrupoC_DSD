package org.upc.project.examplerest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upc.project.examplerest.beans.PaymentDTO;
import org.upc.project.examplerest.service.PaymentService;

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
}
