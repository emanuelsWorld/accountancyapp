package ro.nexttech.internship.domain;


import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    private int invoiceId;
    @Column(name = "issueDate")
    private LocalDate issueDate;
    @Column(name = "dueDate")
    private LocalDate dueDate;
    @Column(name = "invoiceNumber")
    private int number;
    @Column(name = "invoice_total")
    private double invoiceTotal;
    @Column(name = "payment_total")
    private double paymentTotal;
    @Lob
    @Column(name = "file_data")
    private Blob fileData;
    @ManyToMany(mappedBy = "invoiceEntities")
    private Set<Payment> paymentEntities = new HashSet<>();
    @ManyToOne
    @JoinColumn(nullable = false)
    private Firm firm;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Provider provider;

    public Invoice() {

    }

    public Invoice(int invoiceId, LocalDate issueDate, LocalDate dueDate, int number, double invoiceTotal, double paymentTotal, Blob fileData, Set<Payment> paymentEntities, Firm firm) {
        this.invoiceId = invoiceId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.number = number;
        this.invoiceTotal = invoiceTotal;
        this.paymentTotal = paymentTotal;
        this.fileData = fileData;
        this.paymentEntities = paymentEntities;
        this.firm = firm;
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

    public Set<Payment> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(Set<Payment> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
