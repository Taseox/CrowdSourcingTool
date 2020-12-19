package ch.hegarc.ig.util.StatsUtils;

import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.business.ProjetSet;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExportXls {
    //Définition des lignes à sortir dans le fichier
    final private static String NomProjet = "Projet (nom)";
    final private static String Paye = "Total payé";
    final private static String Recevoir = "Total à recevoir";
    final private static String Global = "Total global";
    final private static String NbDons = "Nombre de don (non-annulé)";
    final private static String Moyenne = "Moyenne des dons";

    //Déclaration des variables pour génération du fichier Excel
    private static int rowNb = 0;
    private static int colNb = 0;
    private static XSSFRow row;
    private static XSSFCell cell;
    private static XSSFWorkbook wb = new XSSFWorkbook();
    private static XSSFSheet sheet ;

    public static void statsProjets(ProjetSet projetSet, String filename) {
        sheet = wb.createSheet(String.valueOf(projetSet.hashCode()));
        row = sheet.createRow(rowNb);

        addLineProjets(NomProjet, projetSet);
        addLineProjets(Paye, projetSet);
        addLineProjets(Recevoir, projetSet);
        addLineProjets(Global, projetSet);
        row = sheet.createRow(++rowNb);
        addLineProjets(NbDons, projetSet);
        addLineProjets(Moyenne, projetSet);

        try (OutputStream fileOut = new FileOutputStream(filename)) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void statsProjet(Projet projet, String filename) {
        sheet = wb.createSheet(String.valueOf(projet.hashCode()));
        row = sheet.createRow(rowNb);

        addLineProjet(NomProjet, projet);
        addLineProjet(Paye, projet);
        addLineProjet(Recevoir, projet);
        addLineProjet(Global, projet);
        row = sheet.createRow(++rowNb);
        addLineProjet(NbDons, projet);
        addLineProjet(Moyenne, projet);

        try (OutputStream fileOut = new FileOutputStream(filename)) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ajoutColonneA(String colonneA) {
        cell = row.createCell(colNb);
        cell.setCellValue(colonneA);
        if (colonneA == Global) {
            policeGras();
        }
        colNb++;
    }

    private static void miseEnPageFin() {
        row = sheet.createRow(++rowNb);
        colNb = 0;
    }

    private static void addLineProjets(String colonneA, ProjetSet projetSet) {
        ajoutColonneA(colonneA);
        for (Projet projet : projetSet.toList()) {
            addColonneProjet(colonneA, projet);
        }
        miseEnPageFin();
    }

    private static void addLineProjet(String colonneA, Projet projet) {
        ajoutColonneA(colonneA);
        addColonneProjet(colonneA, projet);
        miseEnPageFin();
    }

    private static void addColonneProjet(String colonneA, Projet projet) {
            cell = row.createCell(colNb);
            testProjet(colonneA, projet);
            colNb++;
    }

    //Teste quel est le contenu de la colonneA et exécute la bonne fonction
    private static void testProjet(String colonneA,Projet projet) {
        if (colonneA == NomProjet) {
            cell.setCellValue(projet.getName());
        } else if (colonneA == Paye) {
            cell.setCellValue(projet.getSommePayeeProjet());
        }else if (colonneA == Recevoir) {
            cell.setCellValue(projet.getSommeRestanteProjet());
        } else if (colonneA == Global) {
            cell.setCellValue(projet.getSommeTotaleProjet());
            policeGras();
        } else if (colonneA == NbDons) {
            cell.setCellValue(projet.getNbDonsNonAnnuleProjet());
        } else {
            cell.setCellValue(projet.getMoyenneDons());
        }
    }

    private static void policeGras() {
        XSSFCellStyle styleBold = wb.createCellStyle();
        XSSFFont font = wb.createFont ();
        font.setBold(true);
        styleBold.setFont(font);
        cell.setCellStyle(styleBold);
    }
}
