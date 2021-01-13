package ro.nexttech.internship.service;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.dto.InvoiceDto;

import java.util.List;
public interface InvoiceService {
    List<InvoiceDto> searchInvoices(String search,String sortField, String sortDirection, Integer pageSize, Integer pageIndex);

    List<Invoice> findAll(Specification<Invoice> specification);

    InvoiceDto getDtoFromInvoice(Invoice invoice);

    public List<InvoiceDto> getDtoFromInvoiceList(List<Invoice> invoices);

    boolean saveInvoice(Invoice invoice);

    boolean saveInvoiceDto(InvoiceDto invoiceDto);

    Invoice getInvoiceFromDto(InvoiceDto invoiceDto);

    boolean deleteInvoice(int id);

    Invoice findById(int id);

    void updateInvoiceFromDto(InvoiceDto invoiceDto, Invoice invoice);

}
