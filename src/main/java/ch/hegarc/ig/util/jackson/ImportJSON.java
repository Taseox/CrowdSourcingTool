package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

public class ImportJSON {

    private static final Logger logger = Logger.getLogger(ImportJSON.class.getName());

    public static Set<Projet> run(String filename) throws IOException{
            // ObjectMapper - Ignorer les propriétés inconnues
            ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Set<Projet> projets = om.readValue(new File(filename), new TypeReference<Set<Projet>>() {
            });

        return projets;
    }
}