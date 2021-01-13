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

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private FirmService firmService;

    @Autowired
    private PaymentService paymentService;

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
