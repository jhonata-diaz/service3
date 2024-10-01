package com.example.demo.Service1;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PaymentService {

    public boolean processPayment(Payment payment) {
        // Validar la tarjeta, CVV y fecha de expiración
        if (isCardValid(payment.getCardNumber()) &&
                isCvvValid(payment.getCvv()) &&
                isExpirationDateValid(payment.getExpirationDate())) {

            // Lógica para debitar el monto, aquí se puede agregar la lógica de integración con un sistema de pago
            System.out.println("Pago procesado: " + payment.getAmount());
            return true;
        }
        return false;
    }

    private boolean isCardValid(String cardNumber) {
        // Lógica de validación de tarjeta (ejemplo simplificado)
        return cardNumber.length() == 16; // Debe tener 16 dígitos
    }

    private boolean isCvvValid(String cvv) {
        // Lógica de validación de CVV
        return cvv.length() == 3; // Debe tener 3 dígitos
    }

    private boolean isExpirationDateValid(String expirationDate) {
        // Validar fecha de expiración
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        LocalDate expiration;
        try {
            expiration = LocalDate.parse("01/" + expirationDate, formatter); // Establecemos un día para validación
        } catch (Exception e) {
            return false;
        }
        return expiration.isAfter(LocalDate.now()); // Verifica si la tarjeta no ha expirado
    }
}
