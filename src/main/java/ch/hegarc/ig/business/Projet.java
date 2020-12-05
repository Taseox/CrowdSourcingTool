package ch.hegarc.ig.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Projet implements Comparable<Projet>{

    @JsonProperty("id")
    private long id;
    @JsonProperty("projet")

    private String name;
    private Set<Donateur> donateurs = new HashSet<>();

    public Projet() {
    }

    /**
     * @param donateurs
     * @param name
     * @param id
     */
    public Projet(long id, String name, Set<Donateur> donateurs) {
        super();
        this.id = id;
        this.name = name;
        this.donateurs = donateurs;
        this.trierDonateurs();
    }

    private Projet(String projetName) {
        this.name = projetName;
    }

/*    public static Projet newPopulatedProjet(String projetName){
        Projet projet = new Projet(projetName);
        projet.getDonateurs().add(new Donateur(1, "Guy", "Lafontaine", "lafontaine.guy@gmail.com", "FR", "testadresse", "testville", "CHF", 1000, true, false, "29.11.1992","29.11.1992"));
        projet.getDonateurs().add(new Donateur(2, "Haha", "Lafontaine", "lafontaine.haha@gmail.com", "FR", "testadresse", "testville", "CHF", 1000, true, false, "29.11.1992","29.11.1992"));

        return projet;
    }


    public Projet getProjet() {
        return this;
    }
*/
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

    public Set<Donateur> getDonateurs() {
        return donateurs;
    }

    @Override
    public int compareTo(Projet p) {
        return this.getName().compareTo(p.getName());
    }

    public void trierDonateurs () {
        Stream<Donateur> stream = this.donateurs.stream().sorted(Comparator.comparing(Donateur::getNom).thenComparing(Donateur::getPrenom));
        this.donateurs = stream.collect(Collectors.toSet());
    }

    public void setDonateurs(Set<Donateur> donateurs) {
        this.donateurs = donateurs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Projet : ");
        sb.append(getName());
        sb.append(" numero ");
        sb.append(getId());
        sb.append("\n");
        if (this.donateurs.isEmpty ())
            sb.append ("Pas de donateurs pour ce projet");
        else {
            sb.append ("Les donateurs de ce projet : \n");
            for (Donateur d : getDonateurs()) {
                sb.append(d.toString ()).append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}