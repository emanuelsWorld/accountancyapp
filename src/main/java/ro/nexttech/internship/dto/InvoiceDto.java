package ro.nexttech.internship.dto;

import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.domain.Provider;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InvoiceDto {

    private int invoiceId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private int number;
    private double invoiceTotal;
    private double paymentTotal;
    private Blob fileData;
    private Set<Integer> paymentEntities = new HashSet<>();
    private int firmId;
    private int providerId;

    public InvoiceDto() {
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public double getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(double paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public Blob getFileData() {
        return fileData;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }

    public Set<Integer> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(Set<Integer> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public static InvoiceDto getDtoFromInvoice(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setNumber(invoice.getNumber());
        invoiceDto.setDueDate(invoice.getDueDate());
        invoiceDto.setIssueDate(invoice.getIssueDate());
        invoiceDto.setFileData(invoice.getFileData());
        invoiceDto.setInvoiceTotal(invoice.getInvoiceTotal());
        invoiceDto.setFirmId(invoice.getFirm().getFirmId());
        invoiceDto.setProviderId(invoice.getProvider().getProviderId());
        invoiceDto.setPaymentEntities(
                invoice.getPaymentEntities().stream().map(Payment::getPaymentId).collect(Collectors.toSet()));
        invoiceDto.setPaymentTotal(invoice.getPaymentTotal());

        return invoiceDto;
    }

    public static List<InvoiceDto> getDtoFromInvoiceList(List<Invoice> invoices) {
        return invoices.stream()
                .map(InvoiceDto::getDtoFromInvoice)
                .collect(Collectors.toList());
    }
}
