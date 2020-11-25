package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JacksonWriter {

    private static final Logger logger = Logger.getLogger(JacksonWriter.class.getName());
    
    public static void run(Projet projet, String filename) {
        try {

            ObjectMapper om = new ObjectMapper();

            // Ignorer les champs vide
            //om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            // Ecriture avec pretty print
            om.writerWithDefaultPrettyPrinter().writeValue(new File(filename), projet);
            
            logger.log(Level.INFO, "Fichier " + filename + " créé");
            
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}