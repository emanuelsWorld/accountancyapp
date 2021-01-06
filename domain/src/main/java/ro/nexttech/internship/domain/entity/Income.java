package ro.nexttech.internship.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @Column(name="income_id")
    private int incomeId;
    @Column(name="ammount")
    private double ammount;
    @Column(name="issueDate")
    private Date date;
    @Column(name="name")
    private String name;


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne
    @JoinColumn(name="firm_id", nullable = false)
    private Firm firm;

    public Income() {

    }

    public Income(int incomeId, double ammount, Date date, String name, TransactionType type, Firm firm) {
        this.incomeId = incomeId;
        this.ammount = ammount;
        this.date = date;
        this.name = name;
        this.type = type;
        this.firm = firm;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
