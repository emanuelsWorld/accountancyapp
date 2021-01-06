package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "firms")
public class Firm {
    @Id
    @Column(name="firm_id")
    private int firmId;
    @Column(name="name")
    private String name;
    @Column(name="bank_balance")
    private double bankBalance;
    @Column(name="cash_balance")
    private double cashBalance;
    @Column(name="address")
    private String address;
    @OneToMany(mappedBy = "firm")
    private Set<User> users;
    @OneToMany(mappedBy = "firm")
    private Set<Income> incomes;
    @OneToMany(mappedBy = "firm")
    private Set<Invoice> invoices;
    public Firm() {

    }

    public Firm(int firmId, String name, double bankBalance, double cashBalance, String address, Set<User> users, Set<Income> incomes, Set<Invoice> invoices) {
        this.firmId = firmId;
        this.name = name;
        this.bankBalance = bankBalance;
        this.cashBalance = cashBalance;
        this.address = address;
        this.users = users;
        this.incomes = incomes;
        this.invoices = invoices;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }
}
