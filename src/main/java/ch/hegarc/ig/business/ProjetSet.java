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

    public ProjetSet(){}

    public void add(Projet projet){
        this.projets.add(projet);
    }

    public Projet get(String nom){
        Projet projet = null;
        for (Projet p : projets) {
            if (p.getName().equalsIgnoreCase(nom)) {
                return p;
            }
        }
        return projet;
    }

        public Set<Projet> getList () {
            return  this.projets;
        }
    }

