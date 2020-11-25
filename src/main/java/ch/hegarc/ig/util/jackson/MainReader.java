package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Donateur;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.hegarc.ig.business.Projet;

public class MainReader {

    private static final Logger logger = Logger.getLogger(MainReader.class.getName());

    public static void main(String[] args) {
        try {

            // ObjectMapper - Ignorer les propriétés inconnues
            ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //ObjectMapper om = new ObjectMapper();

            List<Projet> donateurs = om.readValue(new File("donations.json"), new TypeReference<List<Projet>>(){});
            for(Projet p : donateurs){
                System.out.println(p.toString());
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
