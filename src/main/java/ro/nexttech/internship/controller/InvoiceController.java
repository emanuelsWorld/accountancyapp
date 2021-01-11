package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.dto.InvoiceDto;
import ro.nexttech.internship.filters.invoices.InvoiceSpecificationBuilder;

import java.util.List;

@RestController
@RequestMapping("/rest/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping
    public List<InvoiceDto> searchInvoices(@RequestParam(value = "search") String search) {
        Specification<Invoice> spec = InvoiceSpecificationBuilder.getInvoiceSpec(search);
        return InvoiceDto.getDtoFromInvoiceList(invoiceRepository.findAll(spec));
    }

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable("id") int id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping
    public String deleteInvoice() {
        return null;
    }

    @PutMapping("/{id}")
    public String updateInvoice() {
        return null;
    }
}