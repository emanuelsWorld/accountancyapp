package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @Column(name = "income_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int incomeId;
    @Column(name = "ammount")
    private double ammount;
    @Column(name = "issueDate")
    private LocalDate issueDate;
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

    public Income(int incomeId, double ammount, LocalDate issueDate, String incomeName, TransactionType type, Firm firm) {
        this.incomeId = incomeId;
        this.ammount = ammount;
        this.issueDate = issueDate;
        this.incomeName = incomeName;
        this.type = type;
        this.firm = firm;
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
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
