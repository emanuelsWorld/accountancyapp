package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.dto.InvoiceDto;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.security.CustomUserDetails;
import ro.nexttech.internship.service.InvoiceService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    private Principal principal;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public List<InvoiceDto> searchInvoices(@RequestParam(value = "search") String search,
                                           @RequestParam(value = "sortField", required = false, defaultValue = "invoiceId") String sortField,
                                           @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                           @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                           Authentication auth) {

        return invoiceService.searchInvoices(auth.getName(), search, sortField, sortDir, pageSize, pageIndex);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDto, Authentication auth) {
        if (invoiceDto == null) {
            return null;
        } else {
            if(invoiceService.saveInvoiceDto(invoiceDto, auth.getName())) {
            return invoiceDto;
            } else return null;
        }
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable("id") int id, Authentication auth) {
        if (invoiceService.deleteInvoice(id, auth.getName())) {
            return "Invoice with id: " + id + "deleted successfully";
        }
        return "Invoice deletion unsuccessful";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public InvoiceDto updateInvoice(@PathVariable("id") int id, @RequestBody InvoiceDto invoiceDto, Authentication authentication) {
        return invoiceService.updateInvoice(id, invoiceDto, authentication.getName());
    }

}
