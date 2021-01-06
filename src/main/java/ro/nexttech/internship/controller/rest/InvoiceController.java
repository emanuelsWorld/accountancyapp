package ro.nexttech.internship.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.User;

@RestController
@RequestMapping("/rest/invoices")
public class InvoiceController {

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable("id") int id) {
        return null;
    }

    @PutMapping("/create")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete")
    public String deleteInvoice() {
        return null;
    }

    @PostMapping("/update")
    public String updateInvoice() {
        return null;
    }
}
