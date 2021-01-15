package ro.nexttech.internship.dto;

import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.TransactionType;

import javax.persistence.*;
import java.time.LocalDate;

public class IncomeDto {

    private int incomeId;
    private double ammount;
    private LocalDate issueDate;
    private String incomeName;
    private TransactionType type;
    private int firmId;

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

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }
}
