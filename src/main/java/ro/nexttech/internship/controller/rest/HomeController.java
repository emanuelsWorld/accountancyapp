package ro.nexttech.internship.controller.rest;

import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class HomeController {

    @GetMapping("/home")
    public String getHome() {
        return "Testhomepage";
    }


    //for testing Postman
  //  @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setNumber(12234);
        invoices.add(invoice);
        return invoices;
    }


    //for testing Postman
    @PostMapping("/invoices/addInv/{id}/{number}")
    public Invoice addInvoice(@PathVariable("id") int id, @PathVariable("number") int number) {
        Invoice invoice = new Invoice();
        invoice.setNumber(number);
        invoice.setInvoiceId(id);
        return invoice;
    }
}
