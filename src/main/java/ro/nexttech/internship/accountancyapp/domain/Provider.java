package ro.nexttech.internship.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Provider {
    @Id
    @Column(name="provider_id")
    private int id;
    @Column(name="name")
    private int name;
    @Column(name="address")
    private String address;
    @Column(name="CUI")
    private String cui;
    @OneToMany(mappedBy = "provider",cascade= CascadeType.ALL)
    private Set<Invoice> invoices;

    public Provider(){

    }

    public Provider(int id, int name, String address, String cui, Set<Invoice> invoices) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cui = cui;
        this.invoices = invoices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }
}
