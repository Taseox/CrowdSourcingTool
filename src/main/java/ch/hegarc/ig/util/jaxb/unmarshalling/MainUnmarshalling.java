package ch.hegarc.ig.util.jaxb.unmarshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.hegarc.ig.business.*;
import ch.hegarc.ig.cpo.jaxb.Dataset;
import com.sun.org.apache.xml.internal.serialize.IndentPrinter;

public class MainUnmarshalling {

    private static final Logger logger = Logger.getLogger(MainUnmarshalling.class.getName());

    private MainUnmarshalling() {
    }

    private void run() {

        try {
            JAXBContext jc = JAXBContext.newInstance("ch.hegarc.ig.cpo.jaxb");
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            XMLStreamReader in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream("donations.xml"));

            JAXBElement<Dataset> o = unmarshaller.unmarshal(in, Dataset.class);

            Dataset dataset = o.getValue();

            for (Dataset.Record r : dataset.getRecord()) {
                System.out.println(r.getName() + ", donateurs :");
                for (Dataset.Record.Donateurs d : r.getDonateurs()){
                   System.out.println("\t" + d.getNom() + " " + d.getSomme() + " " + d.getMonnaie());
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MainUnmarshalling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new MainUnmarshalling().run();
    }

}
