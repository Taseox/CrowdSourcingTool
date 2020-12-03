package ch.hegarc.ig.util.jaxb.unmarshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.cpo.jaxb.Dataset;

public class JaxbUnmarshalling {

    private static final Logger logger = Logger.getLogger(JaxbUnmarshalling.class.getName());

    public static Set<Projet> run(String filename) {
            JAXBContext jc = JAXBContext.newInstance("ch.hegarc.ig.cpo.jaxb");
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            XMLStreamReader in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));

            JAXBElement<Dataset> o = unmarshaller.unmarshal(in, Dataset.class);

            Dataset dataset = o.getValue();
            Set<Projet> projets = null;

            for (Dataset.Record r : dataset.getRecord()) {
                Projet projet = new Projet(r.getId(), r.getName(), null);
                projet.setDonateurs((List<Donateur>)r.getDonateurs());
                System.out.println(r.getName() + ", donateurs :");
                for (Dataset.Record.Donateurs d : r.getDonateurs()){
                    Donateur donateur = new Donateur(d.getId(), d.getPrenom(), d.getNom(), d.getEmail(), d.)
                    projet.setDonateurs(d.);
                   System.out.println("\t" + d.getNom() + " " + d.getSomme() + " " + d.getMonnaie());
                }
            }
            return dataset;
    }
}
