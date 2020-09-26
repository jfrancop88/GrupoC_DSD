package org.upc.project.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upc.project.payment.beans.EcommerceDTO;
import org.upc.project.payment.service.EcommerceService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AdministratorController {
    @Autowired
    private EcommerceService ecommerceService;

    @PostMapping(value = "/ecommerce")
    public ResponseEntity<?> createEcommerce(@RequestBody EcommerceDTO request) throws Exception {
        return ResponseEntity.ok(ecommerceService.save(request));
    }

    @GetMapping(value = "/ecommerces")
    public ResponseEntity<?> getAllEcommerce() throws Exception {
        return ResponseEntity.ok(ecommerceService.findAll());
    }

    @GetMapping(value = "/ecommerce/{code}")
    public ResponseEntity<?> getAllEcommerce(@PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok(ecommerceService.findById(code));
    }

    @PutMapping(value = "/ecommerce")
    public ResponseEntity<?> updateEcommerce(@RequestBody EcommerceDTO request) throws Exception {
        return ResponseEntity.ok(ecommerceService.update(request));
    }

    @DeleteMapping(value = "/ecommerce/{code}")
    public ResponseEntity<?> deleteEcommerce(@PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok(ecommerceService.delete(code));
    }
}
