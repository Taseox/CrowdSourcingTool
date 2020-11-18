import ch.hegarc.ig.cpo.jaxb.Dataset;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MainJAXB {

    private static final Logger LOG = Logger.getLogger(MainJAXB.class.getName());

    private MainJAXB() {
    }
    
    private void run() {
        try {
            JAXBContext context = JAXBContext.newInstance(Dataset.class);
            // Création du Marshaller : instances -> XML
            Marshaller m = context.createMarshaller();
            // Format de la sortie
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Instanciation de la liste d'étudiant
            Dataset liste = new Dataset();
            // Création du XML
            m.marshal(liste, System.out);

        } catch (JAXBException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void main(String[] args) {
        new MainJAXB().run();
    }

}
