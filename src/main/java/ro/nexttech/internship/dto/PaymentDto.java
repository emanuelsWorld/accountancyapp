package ro.nexttech.internship.dto;

import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.TransactionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PaymentDto {

    private int paymentId;
    private TransactionType type;
    private double ammount;
    private LocalDate date;
    private Integer paymentNumber;
    Set<Integer> invoiceEntities;

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

    public Set<Integer> getInvoiceEntities() {
        return invoiceEntities;
    }

    public void setInvoiceEntities(Set<Integer> invoiceEntities) {
        this.invoiceEntities = invoiceEntities;
    }
}
