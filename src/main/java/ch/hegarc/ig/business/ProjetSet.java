package ch.hegarc.ig.business;

import ch.hegarc.ig.util.jackson.JacksonReader;
import ch.hegarc.ig.util.jackson.JacksonWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class ProjetSet {
    private Set<Projet> projets;

    public ProjetSet(){
        this.projets = new TreeSet<>();
    }

    public void addProjets (Set<Projet> projets) {
        for (Projet projet : projets)
            this.addProjet(projet);
    }

    public void addProjet(Projet projet) {
        Projet projetExisant = this.getProjetName(projet.getName());
        if (projetExisant.getName() == null) {
            projet.trierDonateurs();
            this.projets.add(projet);
        } else {
            projetExisant.addDonateurs(projet.getDonateurs());
        }

    }

/*
    public void addDonateursProjet(Projet projet) {
        for (Donateur donateur : projet.getDonateurs())
            this.projets.addDonateur(donateur);
        projet.trierDonateurs();

    }*/

    public Projet getProjetName(String nom){
        Projet projet = new Projet();
        for (Projet p : this.projets) {
            if (p.getName().equalsIgnoreCase(nom)) {
                projet = p;
            }
        }
        return projet;
    }


    public Set<Projet> getList () {
            return this.projets;
        }

    public List<Projet> toList () {
        return new LinkedList <> (this.projets);
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Projet p : this.projets)
            sb.append(p.toString());

        return sb.toString();
        }
    }

