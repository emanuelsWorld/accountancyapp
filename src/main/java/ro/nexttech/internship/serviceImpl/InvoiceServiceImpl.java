package ro.nexttech.internship.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.dto.InvoiceDto;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.FirmService;
import ro.nexttech.internship.service.InvoiceService;
import ro.nexttech.internship.service.PaymentService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ro.nexttech.internship.filters.invoices.InvoiceSpecificationBuilder;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private FirmService firmService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public List<InvoiceDto> searchInvoices(String search,String sortField, String sortDirection, Integer pageSize, Integer pageIndex) {
        List<InvoiceDto> res = new ArrayList<>();

        Specification<Invoice> spec = InvoiceSpecificationBuilder.getInvoiceSpec(search);

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
        Page<Invoice> pagedRes = this.invoiceRepository.findAll(spec, pageable);

        res = pagedRes.stream()
                .map(this::getDtoFromInvoice)
                .collect(Collectors.toList());

        return res;
    }

//    private InvoiceDto map (Invoice invoice) {
//        InvoiceDto invoiceDto = new InvoiceDto();
//
//        invoiceDto.setInvoiceId(invoice.getInvoiceId());
//        invoiceDto.setNumber(invoice.getInvoiceNumber());
//        invoiceDto.setIssueDate(invoice.getIssueDate());
//        invoiceDto.setDueDate(invoice.getDueDate());
//        invoiceDto.setFileData(invoice.getFileData());
//        invoiceDto.setInvoiceTotal(invoice.getInvoiceTotal());
//        invoiceDto.setFirmId(invoice.getFirm().getFirmId());
//        invoiceDto.setProviderId(invoice.getProvider().getProviderId());
//        invoiceDto.setPaymentEntities(
//                invoice.getPaymentEntities().stream().map(Payment::getPaymentId).collect(Collectors.toSet()));
//        invoiceDto.setPaymentTotal(invoice.getPaymentTotal());
//
//        return invoiceDto;
//    }

    @Override
    public List<Invoice> findAll(Specification<Invoice> specification) {
        return invoiceRepository.findAll(specification);
    }

    @Override
    public boolean saveInvoice(Invoice invoice) {
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveInvoiceDto(InvoiceDto invoiceDto) {
        try {
            invoiceRepository.save(getInvoiceFromDto(invoiceDto));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Invoice getInvoiceFromDto(InvoiceDto invoiceDto) {

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceDto.getInvoiceId());
        invoice.setPaymentTotal(invoiceDto.getPaymentTotal());
        invoice.setInvoiceTotal(invoiceDto.getInvoiceTotal());
        invoice.setFileData(invoiceDto.getFileData());
        invoice.setIssueDate(invoiceDto.getIssueDate());
        invoice.setDueDate(invoiceDto.getDueDate());
        invoice.setFirm(firmService.findById(invoiceDto.getFirmId()));
        invoice.setPaymentEntities(paymentService.findAllByIdList(invoiceDto.getPaymentEntities()));
        invoice.setInvoiceNumber(invoiceDto.getInvoiceNumber());

        return invoice;
    }

    @Override
    public boolean deleteInvoice(int id) {
        try {
            invoiceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Invoice findById(int id) {
        return null;
    }

    @Override
    public void updateInvoiceFromDto(InvoiceDto invoiceDto, Invoice invoice) {
    }

    @Override
    public InvoiceDto getDtoFromInvoice(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDto.setDueDate(invoice.getDueDate());
        invoiceDto.setIssueDate(invoice.getIssueDate());
        invoiceDto.setFileData(invoice.getFileData());
        invoiceDto.setInvoiceTotal(invoice.getInvoiceTotal());
        invoiceDto.setFirmId(invoice.getFirm().getFirmId());
        invoiceDto.setProviderId(invoice.getProvider().getProviderId());
        invoiceDto.setPaymentTotal(invoice.getPaymentTotal());
        invoiceDto.setPaymentEntities(
                invoice.getPaymentEntities().stream()
                        .map(Payment::getPaymentId)
                        .collect(Collectors.toSet()));

        return invoiceDto;
    }

    @Override
    public List<InvoiceDto> getDtoFromInvoiceList(List<Invoice> invoices) {
        return invoices.stream()
                .map(this::getDtoFromInvoice)
                .collect(Collectors.toList());
    }

}
