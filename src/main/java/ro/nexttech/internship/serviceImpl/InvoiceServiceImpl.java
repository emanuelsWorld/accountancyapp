package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.InvoiceService;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Override
    public List<Invoice> findAll(Specification<Invoice> specification) {
        return invoiceRepository.findAll(specification);
    }
}
