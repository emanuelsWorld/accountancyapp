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

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ro.nexttech.internship.filters.invoices.InvoiceSpecificationBuilder;
import ro.nexttech.internship.service.ProviderService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private FirmService firmService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProviderService providerService;


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
        invoice.setProvider(providerService.findProviderById(invoiceDto.getProviderId()));

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
    public Invoice findInvoiceById(int id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public void updateInvoiceFromDto(InvoiceDto invoiceDto, Invoice invoice) {

        if (invoiceDto.getDueDate() != null) {
            invoice.setDueDate(invoiceDto.getDueDate());
        }

        if (invoiceDto.getIssueDate() != null) {
            invoice.setIssueDate(invoiceDto.getIssueDate());
        }

        if (invoiceDto.getFileData() != null) {
            invoice.setFileData(invoiceDto.getFileData());
        }

        if (invoiceDto.getInvoiceId() != 0) {
            invoice.setInvoiceId(invoiceDto.getInvoiceId());
        }

        if (invoiceDto.getInvoiceNumber() != 0) {
            invoice.setInvoiceNumber(invoiceDto.getInvoiceNumber());
        }

        if (invoiceDto.getProviderId() != 0) {
            invoice.setProvider(providerService.findProviderById(invoiceDto.getProviderId()));
        }

        if (invoiceDto.getFirmId() != 0) {
            invoice.setFirm(firmService.findById(invoiceDto.getFirmId()));
        }

        if (invoiceDto.getPaymentTotal() != 0) {
            invoice.setPaymentTotal(invoiceDto.getPaymentTotal());
        }

        if (invoiceDto.getInvoiceTotal() != 0) {
            invoice.setInvoiceTotal(invoiceDto.getInvoiceTotal());
        }

        if (invoiceDto.getPaymentEntities() != null) {
            invoice.setPaymentEntities(paymentService.findAllByIdList(invoiceDto.getPaymentEntities()));
        }
        invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceDto updateInvoice(int id, InvoiceDto invoiceDto) {
        Invoice invoice = findInvoiceById(id);
        updateInvoiceFromDto(invoiceDto, invoice);
        return getDtoFromInvoice(invoice);
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

        if(!invoice.getPaymentEntities().isEmpty()) {
            invoiceDto.setPaymentEntities(
                    invoice.getPaymentEntities().stream()
                            .map(Payment::getPaymentId)
                            .collect(Collectors.toSet()));
        }
        return invoiceDto;
    }

    @Override
    public List<InvoiceDto> getDtoFromInvoiceList(List<Invoice> invoices) {
        return invoices.stream()
                .map(this::getDtoFromInvoice)
                .collect(Collectors.toList());
    }

}
