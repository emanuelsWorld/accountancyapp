package ro.nexttech.internship.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.dto.InvoiceDto;

import java.util.List;
import java.util.Set;

public interface InvoiceService {

    List<InvoiceDto> searchInvoices(String userName, String search,String sortField, String sortDirection, Integer pageSize, Integer pageIndex);

    List<Invoice> findAll(Specification<Invoice> specification);

    InvoiceDto getDtoFromInvoice(Invoice invoice);

    public List<InvoiceDto> getDtoFromInvoiceList(List<Invoice> invoices);

    boolean saveInvoice(Invoice invoice);

    boolean saveInvoiceDto(InvoiceDto invoiceDto, String userName);

    Invoice getInvoiceFromDto(InvoiceDto invoiceDto);

    boolean deleteInvoice(int id, String userName);

    Invoice findById(int id);

    void updateInvoiceFromDto(InvoiceDto invoiceDto, Invoice invoice);

    InvoiceDto updateInvoice(int id, InvoiceDto invoiceDto, String userName);

    Set<Invoice> findAllByIdSet(Set<Integer> idSet);

}
