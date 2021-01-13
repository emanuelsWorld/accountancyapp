package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice {
    private static Map<Integer,Invoice> invoiceMap=new HashMap<>();
    public static Map<Integer, Invoice> getInvoiceMap() {
        return invoiceMap;
    }

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceId;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "invoice_number")
    private int invoiceNumber;
    @Column(name = "invoice_total")
    private double invoiceTotal;
    @Column(name = "payment_total")
    private double paymentTotal;
    @Lob
    @Column(name = "file_data")
    private Blob fileData;
    @ManyToMany(mappedBy = "invoiceEntities")
    private Set<Payment> paymentEntities;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Firm firm;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Provider provider;

    public Invoice() {

    }

    public Invoice(int invoiceId, LocalDate issueDate, LocalDate dueDate, int invoiceNumber, double invoiceTotal, double paymentTotal, Blob fileData, Set<Payment> paymentEntities, Firm firm, Provider provider) {
        this.invoiceId = invoiceId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceTotal = invoiceTotal;
        this.paymentTotal = paymentTotal;
        this.fileData = fileData;
        this.paymentEntities = paymentEntities;
        this.firm = firm;
        this.provider = provider;
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