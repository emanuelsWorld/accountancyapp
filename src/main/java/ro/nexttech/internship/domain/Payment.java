package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="payments")
public class Payment {
    @Id
    @Column(name="payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;
    @Column(name="ammount")
    private double ammount;
    @Column(name="issueDate")
    private LocalDate date;
    @Column(name="payment_number")
    private Integer paymentNumber;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "payments_invoices",
            joinColumns = {@JoinColumn(name = "paymentId")},
            inverseJoinColumns = {@JoinColumn(name = "invoiceId")})
    Set<Invoice> invoiceEntities = new HashSet<>();

    public Payment(){

    }

    public Payment(int paymentId, TransactionType type, double ammount, LocalDate date, Integer paymentNumber, Set<Invoice> invoiceEntities) {
        this.paymentId = paymentId;
        this.type = type;
        this.ammount = ammount;
        this.date = date;
        this.paymentNumber = paymentNumber;
        this.invoiceEntities = invoiceEntities;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Set<Invoice> getInvoiceEntities() {
        return invoiceEntities;
    }

    public void setInvoiceEntities(Set<Invoice> invoiceEntities) {
        this.invoiceEntities = invoiceEntities;
    }
}
