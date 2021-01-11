package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Provider {
    @Id
    @Column(name="provider_id")
    private int providerId;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="CUI")
    private String cui;
    @OneToMany(mappedBy = "provider",cascade= CascadeType.ALL)
    private Set<Invoice> invoices;

    public Provider(){

    }

    public Provider(int providerId, String name, String address, String cui, Set<Invoice> invoices) {
        this.providerId = providerId;
        this.name = name;
        this.address = address;
        this.cui = cui;
        this.invoices = invoices;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
