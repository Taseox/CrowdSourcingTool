package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.business.ProjetSet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
            
            logger.log(Level.INFO, "Fichier <" + filename + "> créé");
            
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public static void run (List<Projet> projets, String fileName) {
        try {
            ObjectMapper om = new ObjectMapper ();

//			Ignorer les champs vide - TODO Copier de la série 5. On garde ?
//			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//			Ecriture avec pretty print
            om.writerWithDefaultPrettyPrinter ().writeValue (new File (fileName), projets);

            logger.log (Level.INFO, "Fichier '" + fileName + "' crée avec succès !");

        } catch (IOException ex) {
            logger.log (Level.SEVERE, null, ex);
        }
    }
}