package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @Column(name = "income_id")
    private int incomeId;
    @Column(name = "ammount")
    private double ammount;
    @Column(name = "issueDate")
    private LocalDate date;
    @Column(name = "income_name")
    private String incomeName;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Firm firm;

    public Income() {

    }

    public Income(int incomeId, double ammount, LocalDate date, String incomeName, TransactionType type, Firm firm) {
        this.incomeId = incomeId;
        this.ammount = ammount;
        this.date = date;
        this.incomeName = incomeName;
        this.type = type;
        this.firm = firm;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String name) {
        this.incomeName = incomeName;
    }


    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
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


    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}
