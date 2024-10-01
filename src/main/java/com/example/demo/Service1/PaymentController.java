package com.example.demo.Service1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/debit")
    public ResponseEntity<Boolean> debitPayment(@RequestBody Payment payment) {
        boolean success = paymentService.processPayment(payment);
        return ResponseEntity.ok(success);
    }
}
