package ro.nexttech.internship.domain;

public class CompanyDetails {

    private String CUI;
    private String denumire;
    private String adresa;
    private String dataActualizarii;
    private boolean scopuriTva;

    public CompanyDetails(){

    }

    public CompanyDetails(String CUI, String denumire, String adresa, String dataActualizarii, boolean scopuriTva) {
        this.CUI = CUI;
        this.denumire = denumire;
        this.adresa = adresa;
        this.dataActualizarii = dataActualizarii;
        this.scopuriTva = scopuriTva;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getDataActualizarii() {
        return dataActualizarii;
    }

    public void setDataActualizarii(String dataActualizarii) {
        this.dataActualizarii = dataActualizarii;
    }

    public boolean isScopuriTva() {
        return scopuriTva;
    }

    public void setScopuriTva(boolean scopuriTva) {
        this.scopuriTva = scopuriTva;
    }

}
