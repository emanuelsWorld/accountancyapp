package ro.nexttech.internship.dto;

import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FirmDto {

    private int firmId;
    private String name;
    private double bankBalance;
    private double cashBalance;
    private String address;
    private Set<Integer> users;
    private Set<Integer> incomes;
    private Set<Integer> invoices;

    public FirmDto() {
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

    public Set<Integer> getUsers() {
        return users;
    }

    public void setUsers(Set<Integer> users) {
        this.users = users;
    }

    public Set<Integer> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Integer> incomes) {
        this.incomes = incomes;
    }

    public Set<Integer> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Integer> invoices) {
        this.invoices = invoices;
    }

    public static FirmDto getDtoFromFirm(Firm firm) {
        FirmDto firmDto = new FirmDto();
        firmDto.setFirmId(firm.getFirmId());
        firmDto.setName(firm.getFirmName());
        firmDto.setAddress(firm.getAddress());
        firmDto.setBankBalance(firm.getBankBalance());
        firmDto.setCashBalance(firm.getCashBalance());
        firmDto.setIncomes(
                firm.getIncomes().stream().map(Income::getIncomeId).collect(Collectors.toSet()));
        firmDto.setInvoices(
                firm.getInvoices().stream().map(Invoice::getInvoiceId).collect(Collectors.toSet()));
        firmDto.setUsers(
                firm.getUsers().stream().map(User::getUserId).collect(Collectors.toSet()));

        return firmDto;
    }

    public static List<FirmDto> getDtoFromFirmList(List<Firm> firms) {
        return firms.stream()
                .map(FirmDto::getDtoFromFirm)
                .collect(Collectors.toList());
    }
}
