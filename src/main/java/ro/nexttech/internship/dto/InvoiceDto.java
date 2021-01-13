package ro.nexttech.internship.dto;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class InvoiceDto {

    private int invoiceId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private int invoiceNumber;
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

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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


}
