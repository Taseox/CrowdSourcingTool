package ch.hegarc.ig.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Donateur {

    private long id;
    @JsonProperty ("prenom")
    private String prenom;
    private String nom;
    private String email;
    private String langue;
    private String adresse;
    private String ville;
    private String monnaie;
    private long somme;
    private boolean paye;
    private boolean annule;
    private String dateDon;
    private String dateVersement;

    public Donateur() {
    }

    /**
     * @param somme
     * @param ville
     * @param monnaie
     * @param paye
     * @param langue
     * @param nom
     * @param annule
     * @param dateDon
     * @param prenom
     * @param dateVersement
     * @param adresse
     * @param id
     * @param email
     */
    public Donateur(long id, String prenom, String nom, String email, String langue, String adresse, String ville, String monnaie, long somme, boolean paye, boolean annule, String dateDon, String dateVersement) {
        super();
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.langue = langue;
        this.adresse = adresse;
        this.ville = ville;
        this.monnaie = monnaie;
        this.somme = somme;
        this.paye = paye;
        this.annule = annule;
        this.dateDon = dateDon;
        this.dateVersement = dateVersement;
    }

    public Donateur(String prenom, String nom) {
        super();
        this.prenom = prenom;
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prNom) {
        this.prenom = prNom;
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

    public boolean isPaid() { return this.paye;}

    public boolean isAnnule() { return this.annule;}

    public String getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(String dateVersement) {
        this.dateVersement = dateVersement;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isAnnule()) {
            sb.append("\t").append(getNom()).append(" ").append(getPrenom()).append(" ").append("a annulé son don de ").append(getSomme()).append(getMonnaie());
        } else {
            sb.append("\t").append(getNom()).append(" ").append(getPrenom()).append(" ").append("a fait un don de ").append(getSomme()).append(getMonnaie());
        }
        return sb.toString();
    }

    public boolean equals(Donateur donateur) {
        return this.nom.equalsIgnoreCase (donateur.getNom ()) && this.prenom.equalsIgnoreCase (donateur.getPrenom ());
    }

}
