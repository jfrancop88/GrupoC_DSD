package org.upc.project.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.project.payment.beans.EcommerceDTO;
import org.upc.project.payment.beans.PaymentDTO;
import org.upc.project.payment.service.EcommerceService;

@RestController
@RequestMapping("/api/v1")
public class AdministratorController {
    @Autowired
    private EcommerceService ecommerceService;

    @PostMapping(value = "/ecommerce")
    public ResponseEntity<?> createEcommerce(@RequestBody EcommerceDTO request) throws Exception {
        return ResponseEntity.ok(ecommerceService.save(request));
    }
}
