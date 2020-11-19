
package ch.hegarc.ig.cpo.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ch.hegarc.ig.cpo.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ch.hegarc.ig.cpo.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Dataset }
     * 
     */
    public Dataset createDataset() {
        return new Dataset();
    }

    /**
     * Create an instance of {@link Dataset.Record }
     * 
     */
    public Dataset.Record createDatasetRecord() {
        return new Dataset.Record();
    }

    /**
     * Create an instance of {@link Dataset.Record.Donateurs }
     * 
     */
    public Dataset.Record.Donateurs createDatasetRecordDonateurs() {
        return new Dataset.Record.Donateurs();
    }

}
