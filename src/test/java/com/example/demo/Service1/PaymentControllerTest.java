package com.example.demo.Service1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDebitPayment_ValidPayment_ReturnsTrue() {
        // Dado
        Payment payment = new Payment("1234567812345678", "123", "12/25", 100.0);
        when(paymentService.processPayment(payment)).thenReturn(true);

        // Cuando
        ResponseEntity<Boolean> response = paymentController.debitPayment(payment);

        // Entonces
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(true, response.getBody());
    }

    @Test
    void testDebitPayment_InvalidPayment_ReturnsFalse() {
        // Dado
        Payment payment = new Payment("1234567812345678", "123", "12/20", 100.0);
        when(paymentService.processPayment(payment)).thenReturn(false);

        // Cuando
        ResponseEntity<Boolean> response = paymentController.debitPayment(payment);

        // Entonces
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(false, response.getBody());
    }
}
