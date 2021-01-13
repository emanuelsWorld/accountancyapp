package ro.nexttech.internship.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="providers")
public class Provider {
    @Id
    @Column(name="provider_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int providerId;
    @Column(name="provider_name")
    private String providerName;
    @Column(name="address")
    private String address;
    @Column(name="CUI")
    private String cui;
    @OneToMany(mappedBy = "provider",cascade= CascadeType.ALL)
    private Set<Invoice> invoices;

    public Provider(){

    }

    public Provider(int providerId, String providerName, String address, String cui, Set<Invoice> invoices) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.address = address;
        this.cui = cui;
        this.invoices = invoices;
    }

    public int getId() {
        return providerId;
    }

    public void setId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
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

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
}

