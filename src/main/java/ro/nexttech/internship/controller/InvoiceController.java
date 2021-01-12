package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.dto.InvoiceDto;
import ro.nexttech.internship.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/rest/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<InvoiceDto> searchInvoices(@RequestParam(value = "search") String search,
                                           @RequestParam(value = "sortField", required = false, defaultValue = "invoiceId") String sortField,
                                           @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                           @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex) {

        return invoiceService.searchInvoices(search, sortField, sortDir, pageSize, pageIndex);
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