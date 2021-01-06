package ro.nexttech.internship.controller.rest;

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

    @PutMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        if (payment == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete")
    public String deletePayment() {
        return null;
    }

    @PostMapping("/update")
    public String updatePayment() {
        return null;
    }
}
