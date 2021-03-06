package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportJSON {

    private static final Logger logger = Logger.getLogger(ExportJSON.class.getName());

    public static void run(Projet projet, String filename) throws IOException {
            ObjectMapper om = new ObjectMapper();
            om.writerWithDefaultPrettyPrinter().writeValue(new File(filename), projet);
            
            logger.log(Level.INFO, "Fichier <" + filename + "> créé");
    }

    public static void run (List<Projet> projets, String fileName) throws IOException {
            ObjectMapper om = new ObjectMapper ();
            om.writerWithDefaultPrettyPrinter ().writeValue (new File (fileName), projets);

            logger.log (Level.INFO, "Fichier '" + fileName + "' créé avec succès !");
    }
}