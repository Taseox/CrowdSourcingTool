package ch.hegarc.ig.util.jaxb.unmarshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.hegarc.ig.cpo.jaxb.Dataset;

public class JaxbUnmarshalling {

    private static final Logger logger = Logger.getLogger(JaxbUnmarshalling.class.getName());

    public static void run(String filename) {
        try {
            JAXBContext jc = JAXBContext.newInstance("ch.hegarc.ig.cpo.jaxb");
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            XMLStreamReader in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));

            JAXBElement<Dataset> o = unmarshaller.unmarshal(in, Dataset.class);

            Dataset dataset = o.getValue();

            for (Dataset.Record r : dataset.getRecord()) {
                System.out.println(r.getName() + ", donateurs :");
                for (Dataset.Record.Donateurs d : r.getDonateurs()){
                   System.out.println("\t" + d.getNom() + " " + d.getSomme() + " " + d.getMonnaie());
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(JaxbUnmarshalling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
