package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="payments")
public class Payment {
    @Id
    @Column(name = "payment_id")
    private int paymentId;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;
    @Column(name="ammount")
    private double ammount;
    @Column(name="date")
    private Date date;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "payments_invoices",
            joinColumns = {@JoinColumn(name = "paymentId")},
            inverseJoinColumns = {@JoinColumn(name = "invoiceId")})
    Set<Invoice> invoiceEntities = new HashSet<>();

    public Payment(){

    }

    public Payment(int paymentId, TransactionType type, double ammount, Date date, Set<Invoice> invoiceEntities) {
        this.paymentId = paymentId;
        this.type = type;
        this.ammount = ammount;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Invoice> getInvoiceEntities() {
        return invoiceEntities;
    }

    public void setInvoiceEntities(Set<Invoice> invoiceEntities) {
        this.invoiceEntities = invoiceEntities;
    }
}
