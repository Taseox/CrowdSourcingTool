package ch.hegarc.ig.util.StatsUtils;

import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.business.ProjetSet;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExportXls {
    final private static String NomProjet = "Projet (nom)";
    final private static String Paye = "Total payé";
    final private static String Recevoir = "Total à recevoir";
    final private static String Global = "Total global";
    final private static String NbDons = "Nombre de don (non-annulé)";
    final private static String Moyenne = "Moyenne des dons";

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

    private static void addLineProjets(String colonneA, ProjetSet projetSet) {
        cell = row.createCell(colNb);
        cell.setCellValue(colonneA);
        colNb++;

        for (Projet projet : projetSet.toList()) {
            addColonneProjet(colonneA, projet);
        }

        row = sheet.createRow(++rowNb);
        colNb = 0;
    }

    private static void addLineProjet(String colonneA, Projet projet) {
        cell = row.createCell(colNb);
        cell.setCellValue(colonneA);
        colNb++;

        addColonneProjet(colonneA, projet);

        row = sheet.createRow(++rowNb);
        colNb = 0;
    }

    private static void addColonneProjet(String colonneA, Projet projet) {
            cell = row.createCell(colNb);
            testProjet(colonneA, projet);
            colNb++;
    }

    private static void testProjet(String colonneA,Projet projet) {
        if (colonneA.endsWith(NomProjet)) {
            cell.setCellValue(projet.getName());
        } else if (colonneA.endsWith(Paye)) {
            cell.setCellValue(projet.getSommePayeeProjet());
        }else if (colonneA.endsWith(Recevoir)) {
            cell.setCellValue(projet.getSommeRestanteProjet());
        } else if (colonneA.endsWith(Global)) {
            cell.setCellValue(projet.getSommeTotaleProjet());
        } else if (colonneA.endsWith(NbDons)) {
            cell.setCellValue(projet.getNbDonsNonAnnuleProjet());
        } else {
            cell.setCellValue(projet.getMoyenne());
        }
    }

}
