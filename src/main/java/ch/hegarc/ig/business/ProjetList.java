package ch.hegarc.ig.business;

import ch.hegarc.ig.util.jackson.JacksonReader;
import ch.hegarc.ig.util.jackson.JacksonWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjetList {
    //La clef est le nom du projet
    private HashMap<String, Projet> projets;

    public ProjetList(){}

    public void add(String clef, Projet projet){
        this.projets.put(clef, projet);
    }

    public Projet get(String clef){
        return this.projets.get(clef);
    }

    public Collection<Projet> getAll(){
        return projets.values();
    }
}
