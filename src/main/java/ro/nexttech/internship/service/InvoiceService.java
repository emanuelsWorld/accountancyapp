package ro.nexttech.internship.service;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findAll(Specification<Invoice> specification);
}
