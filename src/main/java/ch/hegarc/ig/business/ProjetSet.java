package ch.hegarc.ig.business;
import java.util.*;

public class ProjetSet {
    private Set<Projet> projets;

    public ProjetSet() {
        this.projets = new TreeSet<>();
    }

    public void addProjets(Set<Projet> projets) {
        for (Projet projet : projets)
            this.addProjet(projet);
    }

    public void addProjet(Projet projet) {
        Projet projetExisant = this.getProjectByName(projet.getName());
        if (projetExisant.getName() == null) {
            projet.trierDonateurs();
            this.projets.add(projet);
        } else {
            projetExisant.addDonateurs(projet.getDonateurs());
        }

    }


    public Projet getProjectByName(String nom) {
        Projet projet = new Projet();
        for (Projet p : this.projets) {
            if (p.getName().equalsIgnoreCase(nom))
                projet = p;
        }
        return projet;
    }

    public void getProjetDonationInfo(Projet projet) {
        Long sommeTotal = Long.valueOf(0);
        Long sommePayee = Long.valueOf(0);
        Long sommeRestante = Long.valueOf(0);

        for (Donateur d : projet.getDonateurs()) {
            if (d.getDateVersement() == null) {
                sommeRestante = +d.getSomme();
            } else {
                sommePayee = +d.getSomme();
            }
        }
        sommeTotal = sommePayee + sommeRestante;

        System.out.println("Somme déjà payée : " + getSommePayeeProjet(projet));
        System.out.println("Somme restante à payer : " + getSommeRestanteProjet(projet));
        System.out.println("Somme totale : " + getSommeTotaleProjet(projet));
    }

    public Long getSommePayeeProjet(Projet projet) {
        Long sommePayee = Long.valueOf(0);
        for (Donateur d : projet.getDonateurs()) {
            if (d.getDateVersement() != null)
                sommePayee = +d.getSomme();
        }
        return sommePayee;
    }

    //Retourne le montant total des dons restant à payer (ceux qui n'ont pas encore de date de versement)
    public Long getSommeRestanteProjet(Projet projet) {
        Long sommeRestante = Long.valueOf(0);
        for (Donateur d : projet.getDonateurs()) {
            if (d.getDateVersement() == null)
                sommeRestante = +d.getSomme();
        }
        return sommeRestante;

    }

        public Long getSommeTotaleProjet (Projet projet){
            Long sommeTotale = Long.valueOf(0);
            for (Donateur d : projet.getDonateurs())
                sommeTotale=+d.getSomme();

            return sommeTotale;
        }



        public String getMedianeEtMoyenne (Projet projet){
            Long total = Long.valueOf(0);
            int nbDonateurs = 0;
            Long moyenne = Long.valueOf(0);
            StringBuilder sb = new StringBuilder("");
            for (Donateur d : projet.getDonateurs()) {
                total = +d.getSomme();
                nbDonateurs = +1;
            }
            moyenne = total / nbDonateurs;
            List<Donateur> list = projet.getDonateurs();
            list.sort(Comparator.comparingDouble(Donateur::getSomme));
            double mediane = list.get(list.size() / 2).getSomme();
            if (list.size() % 2 == 0) mediane = (mediane + list.get(list.size() / 2 - 1).getSomme()) / 2;

            sb.append("Somme moyenne des dons : ");
            sb.append(moyenne);
            sb.append("\n");
            sb.append("Somme médiane des dons : ");
            sb.append(mediane);
            return sb.toString();
        }



        public int size() { return this.projets.size();}

        public List<Projet> toList() {
            return new LinkedList<>(this.projets);
        }

        public String toString () {
            StringBuilder sb = new StringBuilder();
            for (Projet p : this.projets)
                sb.append(p.toString());

            return sb.toString();
        }
    }

