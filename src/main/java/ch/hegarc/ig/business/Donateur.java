package ch.hegarc.ig.business;

public class Donateur {

    private long id;
    private String prNom;
    private String nom;
    private String email;
    private String langue;
    private String adresse;
    private String ville;
    private String monnaie;
    private long somme;
    private String dateDon;
    private String dateVersement;

    public Donateur() {
    }

    /**
     * @param somme
     * @param ville
     * @param monnaie
     * @param pay
     * @param langue
     * @param nom
     * @param annul
     * @param dateDon
     * @param prNom
     * @param dateVersement
     * @param adresse
     * @param id
     * @param email
     */
    public Donateur(long id, String prNom, String nom, String email, String langue, String adresse, String ville, String monnaie, long somme, boolean pay, boolean annul, String dateDon, String dateVersement) {
        super();
        this.id = id;
        this.prNom = prNom;
        this.nom = nom;
        this.email = email;
        this.langue = langue;
        this.adresse = adresse;
        this.ville = ville;
        this.monnaie = monnaie;
        this.somme = somme;
        this.dateDon = dateDon;
        this.dateVersement = dateVersement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrNom() {
        return prNom;
    }

    public void setPrNom(String prNom) {
        this.prNom = prNom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public long getSomme() {
        return somme;
    }

    public void setSomme(long somme) {
        this.somme = somme;
    }

    public String getDateDon() {
        return dateDon;
    }

    public void setDateDon(String dateDon) {
        this.dateDon = dateDon;
    }

    public String getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(String dateVersement) {
        this.dateVersement = dateVersement;
    }
}
