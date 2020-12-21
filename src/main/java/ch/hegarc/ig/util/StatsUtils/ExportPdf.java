package ch.hegarc.ig.util.StatsUtils;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public class ExportPdf {

    public static String writePdf(Projet projet) throws IOException {
        PDDocument document = new PDDocument();

        //Première page :
        PDPageContentStream cos = newPage(document);

        //Titre du document, différent des autres titres qui utilisent la fonction newTitle
        cos.setFont (PDType1Font.HELVETICA_BOLD, 20);

        cos.setLeading (17.5f);
        cos.showText ("Bilan du projet " + projet.getName());

        newTitle(cos, "Les 5 plus gros donateurs :");
        for (Donateur donateur : projet.get5Best()) {
            cos.showText(donateur.getNom() + " " + donateur.getPrenom() + " : " + donateur.getSomme() + " " + donateur.getMonnaie());
            cos.newLine();
        }

        newTitle(cos, "Donateurs n'ayant pas encore payé leur don (et non annulé) : ");
        for (Donateur donateur : projet.getNonPaye()) {
            cos.showText(donateur.getNom() + " " + donateur.getPrenom() + " : " + donateur.getSomme() + " " + donateur.getMonnaie());
            cos.newLine();
        }

        newTitle(cos, "La somme totale du projet (payée ou non) :");
        cos.showText(projet.getSommeTotaleProjet().toString());

        newTitle(cos, "La commission de l'entreprise pour ce projet (5%) :");
        cos.showText(projet.getComission().toString());

        //Clôture du ContentStream de la première page
        cos.endText ();
        cos.close ();

        //Deuxième page
        PDPageContentStream cos2 = newPage(document);

        newTitle(cos2, "Les mails des donateurs du projet :");
        String[] emails = projet.getDonateurMail().split(";");
        for (String email : emails) {
            cos2.showText(email + ";");
            cos2.newLine();
        }

        //Clôture du ContentStream de la deuxième page
        cos2.endText();
        cos2.close();

        //Fin du document
        String outputFileName = "Bilan du projet " + projet.getName() + ".pdf";
        document.save(outputFileName);
        document.close();
        return outputFileName;
    }

    public static PDPageContentStream newPage(PDDocument document) throws IOException {
        PDPage page = new PDPage ();
        document.addPage (page);
        PDPageContentStream cos = new PDPageContentStream (document, page);
        cos.beginText();
        cos.newLineAtOffset (50, 750);
        return cos;
    }

    public static void newTitle(PDPageContentStream cos, String title) throws IOException {
        cos.newLine();
        cos.newLine();
        cos.setFont (PDType1Font.HELVETICA_BOLD, 16);
        cos.setLeading(14.5f);
        cos.showText(title);
        cos.newLine();
        cos.newLine();
        cos.setFont(PDType1Font.HELVETICA, 12);
    }
}