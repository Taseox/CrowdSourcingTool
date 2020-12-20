package ch.hegarc.ig.util.StatsUtils;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public class ExportPdf {

    public static void writePdf(Projet projet) throws IOException {
        String outputFileName = "Bilan du projet " + projet.getName() + ".pdf";

        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage();
        document.addPage(page1);
        PDPageContentStream cos = new PDPageContentStream (document, page1);

        cos.setFont (PDType1Font.HELVETICA_BOLD, 20);
        cos.beginText ();
        cos.newLineAtOffset (50, 750);
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

        cos.endText ();
        cos.close ();

        //Nouvelle page
        PDPage page2 = new PDPage ();
        document.addPage (page2);
        PDPageContentStream cos2 = new PDPageContentStream (document, page2);
        cos2.beginText();
        cos2.newLineAtOffset (50, 750);

        newTitle(cos2, "Les mails des donateurs du projet :");
        String[] emails = projet.getDonateurMail().split(";");
        for (String email : emails) {
            cos2.showText(email + ";");
            cos2.newLine();
        }

        cos2.endText();
        cos2.close();


        document.save (outputFileName);
        document.close ();
    }

    public static void newTitle(PDPageContentStream cos, String title) throws IOException {
        cos.newLine();
        cos.newLine();
        cos.setFont (PDType1Font.HELVETICA_BOLD, 16);
        cos.setLeading(14.5f);
        cos.showText(title);
        cos.newLine();
        cos.setFont(PDType1Font.HELVETICA, 12);
    }
}