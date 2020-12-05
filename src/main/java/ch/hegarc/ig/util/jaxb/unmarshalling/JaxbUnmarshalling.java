package ch.hegarc.ig.util.jaxb.unmarshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.cpo.jaxb.Dataset;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

public class JaxbUnmarshalling {

    private static final Logger logger = Logger.getLogger(JaxbUnmarshalling.class.getName());

    public static Set<Projet> run(String filename) throws Exception {
            JAXBContext jc = JAXBContext.newInstance("ch.hegarc.ig.cpo.jaxb");
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            XMLStreamReader in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));

            JAXBElement<Dataset> o = unmarshaller.unmarshal(in, Dataset.class);

            Dataset dataset = o.getValue();
            Set<Projet> projets = new HashSet<>();

            for (Dataset.Record r : dataset.getRecord()) {
                Projet projet = new Projet(r.getId(), r.getName(), new HashSet<>());
                for (Dataset.Record.Donateurs d : r.getDonateurs()){
                    Donateur donateur = new Donateur(d.getId(), d.getPrenom(), d.getNom(), d.getEmail(), d.getLangue(), d.getAdresse(), d.getVille(), d.getMonnaie(), d.getSomme(), d.getPaye(), d.getAnnule(), d.getDateDon(), d.getDateVersement());
                    projet.getDonateurs().add(donateur);
                }
                projets.add(projet);
            }
            return projets;
    }
}
