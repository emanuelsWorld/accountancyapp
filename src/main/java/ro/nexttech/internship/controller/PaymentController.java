package ro.nexttech.internship.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.domain.Payment;

@RestController
@RequestMapping("/rest/payments")
public class PaymentController {

    @GetMapping("/{id}")
    public Income getPayment(@PathVariable("id") int id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        if (payment == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping
    public String deletePayment() {
        return null;
    }

    @PutMapping("/{id}")
    public String updatePayment() {
        return null;
    }
}
