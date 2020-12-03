
package ch.hegarc.ig.cpo.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="record" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="donateurs" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="langue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="monnaie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="somme" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="paye" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="annule" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="dateDon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="dateVersement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "record"
})
@XmlRootElement(name = "dataset")
public class Dataset {

    @XmlElement(required = true)
    protected List<Dataset.Record> record;

    /**
     * Gets the value of the record property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the record property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dataset.Record }
     * 
     * 
     */
    public List<Dataset.Record> getRecord() {
        if (record == null) {
            record = new ArrayList<Dataset.Record>();
        }
        return this.record;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="donateurs" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="langue" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="monnaie" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="somme" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="paye" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="annule" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="dateDon" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="dateVersement" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "name",
        "donateurs"
    })
    public static class Record {

        protected int id;
        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected List<Dataset.Record.Donateurs> donateurs;

        /**
         * Obtient la valeur de la propriété id.
         * 
         */
        public int getId() {
            return id;
        }

        /**
         * Définit la valeur de la propriété id.
         * 
         */
        public void setId(int value) {
            this.id = value;
        }

        /**
         * Obtient la valeur de la propriété name.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Définit la valeur de la propriété name.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the donateurs property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the donateurs property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDonateurs().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Dataset.Record.Donateurs }
         * 
         * 
         */
        public List<Dataset.Record.Donateurs> getDonateurs() {
            if (donateurs == null) {
                donateurs = new ArrayList<Dataset.Record.Donateurs>();
            }
            return this.donateurs;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="langue" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="monnaie" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="somme" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="paye" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="annule" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="dateDon" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="dateVersement" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "id",
            "prenom",
            "nom",
            "email",
            "langue",
            "adresse",
            "ville",
            "monnaie",
            "somme",
            "paye",
            "annule",
            "dateDon",
            "dateVersement"
        })
        public static class Donateurs {

            protected int id;
            @XmlElement(required = true)
            protected String prenom;
            @XmlElement(required = true)
            protected String nom;
            @XmlElement(required = true)
            protected String email;
            @XmlElement(required = true)
            protected String langue;
            @XmlElement(required = true)
            protected String adresse;
            @XmlElement(required = true)
            protected String ville;
            @XmlElement(required = true)
            protected String monnaie;
            protected int somme;
            @XmlElement(required = true)
            protected Boolean paye;
            @XmlElement(required = true)
            protected Boolean annule;
            @XmlElement(required = true)
            protected String dateDon;
            @XmlElement(required = true)
            protected String dateVersement;

            /**
             * Obtient la valeur de la propriété id.
             * 
             */
            public int getId() {
                return id;
            }

            /**
             * Définit la valeur de la propriété id.
             * 
             */
            public void setId(int value) {
                this.id = value;
            }

            /**
             * Obtient la valeur de la propriété prenom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * Définit la valeur de la propriété prenom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }

            /**
             * Obtient la valeur de la propriété nom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNom() {
                return nom;
            }

            /**
             * Définit la valeur de la propriété nom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propriété email.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmail() {
                return email;
            }

            /**
             * Définit la valeur de la propriété email.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmail(String value) {
                this.email = value;
            }

            /**
             * Obtient la valeur de la propriété langue.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLangue() {
                return langue;
            }

            /**
             * Définit la valeur de la propriété langue.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLangue(String value) {
                this.langue = value;
            }

            /**
             * Obtient la valeur de la propriété adresse.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAdresse() {
                return adresse;
            }

            /**
             * Définit la valeur de la propriété adresse.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAdresse(String value) {
                this.adresse = value;
            }

            /**
             * Obtient la valeur de la propriété ville.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVille() {
                return ville;
            }

            /**
             * Définit la valeur de la propriété ville.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVille(String value) {
                this.ville = value;
            }

            /**
             * Obtient la valeur de la propriété monnaie.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMonnaie() {
                return monnaie;
            }

            /**
             * Définit la valeur de la propriété monnaie.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMonnaie(String value) {
                this.monnaie = value;
            }

            /**
             * Obtient la valeur de la propriété somme.
             * 
             */
            public int getSomme() {
                return somme;
            }

            /**
             * Définit la valeur de la propriété somme.
             * 
             */
            public void setSomme(int value) {
                this.somme = value;
            }

            /**
             * Obtient la valeur de la propriété paye.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public Boolean getPaye() {
                return paye;
            }

            /**
             * Définit la valeur de la propriété paye.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPaye(Boolean value) {
                this.paye = value;
            }

            /**
             * Obtient la valeur de la propriété annule.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public Boolean getAnnule() {
                return annule;
            }

            /**
             * Définit la valeur de la propriété annule.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAnnule(Boolean value) {
                this.annule = value;
            }

            /**
             * Obtient la valeur de la propriété dateDon.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDateDon() {
                return dateDon;
            }

            /**
             * Définit la valeur de la propriété dateDon.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDateDon(String value) {
                this.dateDon = value;
            }

            /**
             * Obtient la valeur de la propriété dateVersement.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDateVersement() {
                return dateVersement;
            }

            /**
             * Définit la valeur de la propriété dateVersement.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDateVersement(String value) {
                this.dateVersement = value;
            }

        }

    }

}
