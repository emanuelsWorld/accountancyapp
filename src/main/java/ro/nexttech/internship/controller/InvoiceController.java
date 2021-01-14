package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.dto.InvoiceDto;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.service.InvoiceService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    HttpServletRequest context;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceDto> searchInvoices(@RequestParam(value = "search") String search,
                                           @RequestParam(value = "sortField", required = false, defaultValue = "invoiceId") String sortField,
                                           @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                           @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex) {

        return invoiceService.searchInvoices(search, sortField, sortDir, pageSize, pageIndex);
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
    public InvoiceDto updateInvoice(@PathVariable("id") int id, @RequestBody InvoiceDto invoiceDto) {
        System.out.println("USER" + context.getUserPrincipal());
        return invoiceService.updateInvoice(id, invoiceDto);
    }

}
