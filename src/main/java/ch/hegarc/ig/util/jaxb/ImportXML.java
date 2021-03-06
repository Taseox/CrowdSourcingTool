package ch.hegarc.ig.util.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.*;
import java.util.logging.Logger;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.cpo.jaxb.Dataset;

public class ImportXML {

    private static final Logger logger = Logger.getLogger(ImportXML.class.getName());

    public static Set<Projet> run(String filename) throws Exception {
            JAXBContext jc = JAXBContext.newInstance("ch.hegarc.ig.cpo.jaxb");
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            XMLStreamReader in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));

            JAXBElement<Dataset> o = unmarshaller.unmarshal(in, Dataset.class);

            Dataset dataset = o.getValue();
            Set<Projet> projets = new TreeSet<>();

            for (Dataset.Record r : dataset.getRecord()) {
                Projet projet = new Projet(r.getId(), r.getName(), new LinkedList<>());

                for (Dataset.Record.Donateurs d : r.getDonateurs()){
                    Donateur donateur = new Donateur(d.getId(), d.getPrenom(), d.getNom(), d.getEmail(), d.getLangue(), d.getAdresse(), d.getVille(), d.getMonnaie(), d.getSomme(), d.getPaye(), d.getAnnule(), d.getDateDon(), d.getDateVersement());
                    projet.getDonateurs().add(donateur);
                }
                projets.add(projet);
            }
            TreeSet returnSet = new TreeSet(projets);
            return returnSet;
    }
}
