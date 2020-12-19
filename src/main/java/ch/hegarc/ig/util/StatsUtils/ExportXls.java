package ch.hegarc.ig.util.StatsUtils;

import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.business.ProjetSet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExportXls {
    private static int rowNb = 0;
    private static int colNb = 0;
    private static XSSFRow row;
    private static XSSFCell cell;

    private static XSSFWorkbook wb = new XSSFWorkbook();
    private static XSSFSheet sheet ;

    public static void run(ProjetSet projetSet, String filename) {

        // Nouveau document, nouvel onglet
        sheet = wb.createSheet(String.valueOf(projetSet.hashCode()));
/*
        int rowNb = 0;
        int colNb = 0;
        */

        row = sheet.createRow(rowNb);
        XSSFCell cell;


        addLine("Projet (nom)", projetSet);



        cell = row.createCell(colNb);
        cell.setCellValue("Projet (nom)");
        colNb++;
        //Noms des projets
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getName());
            colNb++;
        }
        row = sheet.createRow(++rowNb);


        //Total payé
        colNb = 0;
        cell = row.createCell(colNb);
        cell.setCellValue("Total payé");
        colNb++;
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getSommePayeeProjet());
            colNb++;
        }
        row = sheet.createRow(++rowNb);

        //Total à recevoir
        colNb = 0;
        cell = row.createCell(colNb);
        cell.setCellValue("Total à recevoir");
        colNb++;
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getSommeRestanteProjet());
            colNb++;
        }
        row = sheet.createRow(++rowNb);

        //Total final
        colNb = 0;
        cell = row.createCell(colNb);
        cell.setCellValue("Total");
        colNb++;
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getSommeTotaleProjet());
            colNb++;
        }
        row = sheet.createRow(rowNb+=2);

        //Nombre de dons (non-annulés)
        colNb = 0;
        cell = row.createCell(colNb);
        cell.setCellValue("Nombre de don (non-annulé)");
        colNb++;
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getNbDonsNonAnnuleProjet());
            colNb++;
        }
        row = sheet.createRow(++rowNb);

        //Moyenne des dons
        colNb = 0;
        cell = row.createCell(colNb);
        cell.setCellValue("Moyenne des dons");
        colNb++;
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getMoyenne());
            colNb++;
        }


            try (OutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    private static void addLine(String colonneA, ProjetSet projetSet) {
        cell = row.createCell(colNb);
        cell.setCellValue(colonneA);
        colNb++;
        //Noms des projets
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getName());
            colNb++;
        }
        row = sheet.createRow(++rowNb);
    }
    }
