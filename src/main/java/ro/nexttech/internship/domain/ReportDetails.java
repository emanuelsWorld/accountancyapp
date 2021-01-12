package ro.nexttech.internship.domain;

import java.time.LocalDate;

public class ReportDetails {
    private LocalDate date;
    private Double ammount;
    private boolean credit;
    public ReportDetails(){

    }

    public ReportDetails(LocalDate date, Double ammount, boolean credit) {
        this.date = date;
        this.ammount = ammount;
        this.credit = credit;
    }

    public ReportDetails(LocalDate date, Double ammount) {
        this.date = date;
        this.ammount = ammount;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmmount() {
        return ammount;
    }

    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }
}
