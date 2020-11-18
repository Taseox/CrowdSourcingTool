package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.DonateurList;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainReader {

    private static final Logger logger = Logger.getLogger(MainReader.class.getName());

    public static void main(String[] args) {
        try {

            // ObjectMapper - Ignorer les propriétés inconnues
            //ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            ObjectMapper om = new ObjectMapper();


            //TODO : fix this shit right here --v--
            DonateurList[] students = om.readValue(new File("donations.json"), DonateurList[].class);

            logger.log(Level.INFO, students.toString());


        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
