package ch.hegarc.ig.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Projet {

    @JsonProperty("id")
    private long id;
    @JsonProperty("projet")
    private String name;
    private List<Donateur> donateurs = new ArrayList<>();

    public Projet() {
    }

    /**
     * @param donateurs
     * @param name
     * @param id
     */
    public Projet(long id, String name, List<Donateur> donateurs) {
        super();
        this.id = id;
        this.name = name;
        this.donateurs = donateurs;
    }

    private Projet(String projetName) {
        this.name = projetName;
    }

    public static Projet newPopulatedProjet(String projetName){
        Projet projet = new Projet(projetName);
        projet.getDonateurs().add(new Donateur(1, "Guy", "Lafontaine", "lafontaine.guy@gmail.com", "FR", "testadresse", "testville", "CHF", 1000, true, false, "29.11.1992","29.11.1992"));
        projet.getDonateurs().add(new Donateur(2, "Haha", "Lafontaine", "lafontaine.haha@gmail.com", "FR", "testadresse", "testville", "CHF", 1000, true, false, "29.11.1992","29.11.1992"));

        return projet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Donateur> getDonateurs() {
        return donateurs;
    }

    public void setDonateurs(List<Donateur> donateurs) {
        this.donateurs = donateurs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Projet numero : ");
        sb.append(getId());
        sb.append("\n");
        sb.append("Nom du projet : ");
        sb.append(getName());
        for (Donateur d : getDonateurs()) {
            sb.append(d);
            sb.append("\n");
        }

        return sb.toString();
    }
}