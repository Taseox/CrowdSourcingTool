package ch.hegarc.ig.business;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Projet implements Comparable<Projet>{

    @JsonProperty("id")
    private long id;
    @JsonProperty("projet")

    private String name;
    private List<Donateur> donateurs = new LinkedList<>();

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
        this.trierDonateurs();
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

    public List<Donateur> get5Best()
    {
        Stream<Donateur> stream = donateurs.stream();
        return stream.sorted(Comparator.comparing(Donateur::getSomme))
                .limit(5).collect(Collectors.toList());
    }

    public List<Donateur> getNonPaye()
    {
        Stream<Donateur> stream = donateurs.stream();
        return stream.filter(donateur -> donateur.getDateVersement().equalsIgnoreCase("")).collect(Collectors.toList());
    }

    public Long getNamelistDonations(String nameList){
        Long somme = Long.valueOf(0);
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(nameList, ";");
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        for(String s: tokens){
            for(Donateur d : donateurs){
                if (d.getNom().equals(s)){
                    somme=+d.getSomme();
                }
            }
        }
        return somme;
    }

    public Long getSommePayeeProjet() {
        return this.getDonateurs().stream().filter(donateur -> !donateur.isAnnule() && donateur.isPaid()).mapToLong(Donateur::getSomme).sum();
    }

    public Long getSommeRestanteProjet() {
        return this.getDonateurs().stream().filter(donateur -> !donateur.isAnnule() && !donateur.isPaid()).mapToLong(Donateur::getSomme).sum();
    }

    public Long getSommeTotaleProjet(){
        return this.getDonateurs().stream().filter(donateur -> !donateur.isAnnule()).mapToLong(Donateur::getSomme).sum();
    }

    public int getNbDonsNonAnnuleProjet(){
        int nbDons = 0;
        for (Donateur d : this.getDonateurs()) {
            if (!d.isAnnule())
                ++nbDons;
        }
        return nbDons;
    }


    public Long getMoyenneDons(){
        return (long) this.getDonateurs().stream().mapToLong(Donateur::getSomme).average().orElse(- 100);
    }

    public void addDonateurs(List<Donateur> donateurs) {
        for (Donateur donateur : donateurs)
            addDonateur(donateur);
    }

    public Boolean removeDonateur(String lastName, String firstName) {
        Donateur donateurTest = new Donateur(firstName, lastName);
        Donateur donateurToDelete = new Donateur();
        for(Donateur d : donateurs){
            if(d.equals(donateurTest)) {
                donateurToDelete = d;
            }
        }

        return donateurs.remove(donateurToDelete);
    }

    public boolean addDonateur(Donateur donateur) {
        boolean donateurExistant = false;
        for(Donateur d : donateurs){
            if (d.equals(donateur))
                donateurExistant = true;
        }
        if(donateurExistant == false) {
            donateurs.add(donateur);
            trierDonateurs();
        }
        return !donateurExistant;
    }

    @Override
    public int compareTo(Projet p) {
        return this.getName().compareTo(p.getName());
    }

    public void trierDonateurs () {
        this.donateurs = this.donateurs.stream().sorted(Comparator.comparing(Donateur::getNom).thenComparing(Donateur::getPrenom)).collect(Collectors.toList());
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