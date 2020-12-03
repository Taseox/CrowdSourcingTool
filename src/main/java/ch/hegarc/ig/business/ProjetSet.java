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

    public void addProjets(Set<Projet> projets){
        for (Projet p : projets){
            projets.add(p);
        }
    }

    public Projet get(String nom){
        Projet projet = new Projet();
        for (Projet p : this.projets) {
            if (p.getName().equalsIgnoreCase(nom)) {
                projet = p;

            }else{
                System.out.printf("FAIL");
            }
        }
        return projet;
    }

        public Set<Projet> getList () {
            return  this.projets;
        }

 /*       public String toString(){
        StringBuilder sb = new StringBuilder();
            for(Projet p : projets){
                sb.append(p.toString());
            }
            return sb.toString();
        }
*/
    }

