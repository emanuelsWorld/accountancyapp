package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.dto.PaymentDto;
import ro.nexttech.internship.service.PaymentService;

@RestController
@RequestMapping("/rest/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/{id}")
    public PaymentDto getPayment(@PathVariable("id") int id) {
        return paymentService.getDtoFromPayment(paymentService.findById(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public PaymentDto createPayment(@RequestBody PaymentDto paymentDto, Authentication auth) {
        if (paymentDto == null) {
            return null;
        } else {
            return paymentService.savePaymentDto(paymentDto, auth.getName());
        }
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable("id") int id) {
        if(paymentService.deleteById(id)) {
            return "Payment with id " + id + " deleted";
        }
        return "Deletion unsuccessful";

    }

    @PutMapping("/{id}")
    public String updatePayment() {
        return null;
    }
}
