package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.dto.InvoiceDto;
import ro.nexttech.internship.filters.invoices.InvoiceSpecificationBuilder;
import ro.nexttech.internship.service.InvoiceService;
import ro.nexttech.internship.serviceImpl.InvoiceServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/rest/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceDto> searchInvoices(@RequestParam(value = "search") String search) {
        Specification<Invoice> spec = InvoiceSpecificationBuilder.getInvoiceSpec(search);
        return invoiceService.getDtoFromInvoiceList(invoiceService.findAll(spec));
    }

    @PostMapping
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        } else {
            invoiceService.saveInvoiceDto(invoiceDto);
            return invoiceDto;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable("id") int id) {
        if(invoiceService.deleteInvoice(id)) {
            return "Invoice with id: " + id + "deleted successfully";
        }
        return "Invoice deletion unsuccessful";
    }

    @PutMapping("/{id}")
    public String updateInvoice() {
        return null;
    }
}
