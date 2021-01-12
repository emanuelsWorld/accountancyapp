package ro.nexttech.internship.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.dto.InvoiceDto;
import ro.nexttech.internship.filters.invoices.InvoiceSpecificationBuilder;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDto> searchInvoices(String search,String sortField, String sortDirection, Integer pageSize, Integer pageIndex) {
        List<InvoiceDto> res = new ArrayList<>();

        Specification<Invoice> spec = InvoiceSpecificationBuilder.getInvoiceSpec(search);

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
        Page<Invoice> pagedRes = this.invoiceRepository.findAll(spec, pageable);

        res = pagedRes.stream()
                .map(invoice -> map(invoice))
                .collect(Collectors.toList());

        return res;
    }

    private InvoiceDto map (Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setNumber(invoice.getInvoiceNumber());
        invoiceDto.setIssueDate(invoice.getIssueDate());
        invoiceDto.setDueDate(invoice.getDueDate());
        invoiceDto.setFileData(invoice.getFileData());
        invoiceDto.setInvoiceTotal(invoice.getInvoiceTotal());
        invoiceDto.setFirmId(invoice.getFirm().getFirmId());
        invoiceDto.setProviderId(invoice.getProvider().getProviderId());
        invoiceDto.setPaymentEntities(
                invoice.getPaymentEntities().stream().map(Payment::getPaymentId).collect(Collectors.toSet()));
        invoiceDto.setPaymentTotal(invoice.getPaymentTotal());

        return invoiceDto;
    }
}