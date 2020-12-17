package ch.hegarc.ig.util.StatsUtils;

import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.business.ProjetSet;
import ch.hegarc.ig.cpo.business.Note;
import ch.hegarc.ig.cpo.business.Student;
import ch.hegarc.ig.cpo.business.StudentList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class MainXls {

    public static final String MOYENNE = "AVERAGEA(";
    public static final char VIRGULE = ',';
    public static final char CLOSE = ')';

    private void run(ProjetSet projetSet, String filename) {
        ObjectMapper om = new ObjectMapper();

        //ArrayList<Projet> projets = om.readValue(new File(filename), Projet.class);

        // Nouveau document, nouvel onglet
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(String.valueOf(projetSet.hashCode()));

        int rowNb = 0;
        int colNb = 0;
        XSSFRow row = sheet.createRow(rowNb);
        XSSFCell cell;


        //Noms des projets
        for (Projet p : projetSet.toList()) {
            cell = row.createCell(colNb);
            cell.setCellValue(p.getName());
            colNb++;
        }
        rowNb++;


        //Total payé
        colNb = 0;

        cell = row.createCell(colNb);
        cell.setCellValue("Total payé :");
        for (colNb = 1; colNb < projetSet.toList().size(); colNb++) {
                cell = row.createCell(colNb);
                cell.setCellValue(projetSet.getSommePayeeProjet(projetSet.getList()));
                sheet.addMergedRegion(new CellRangeAddress(
                        rowNb, rowNb,
                        column, column + 1));
                column += 2;
            }
            //Total à recevoir

            //Total final

            //Nombre de dons (non-annulés)

            //Moyenne des dons


            // Reprendre la liste des modules


            // Entête des modules
            for (String mod : modules) {
                cell = row.createCell(columnModule);
                cell.setCellValue(mod);
                sheet.addMergedRegion(new CellRangeAddress(
                        rowNb, rowNb,
                        columnModule, columnModule + 1));
                columnModule += 2;
            }

            Set<String> coursXls = new HashSet<>();
            row = sheet.createRow(++rowNb);
            cell = row.createCell(colNb++);
            cell.setCellValue("Prénom");
            cell = row.createCell(colNb++);
            cell.setCellValue("Nom");
            cell = row.createCell(colNb++);
            cell.setCellValue("Age");

            // Entêtes des colonnes de cours
            for (String mod : modules) {
                // Reprendre les cours de chaque module
                Set<String> cours = NotesUtil.coursModuleList(students, mod);
                for (String co : cours) {
                    cell = row.createCell(colNb++);
                    cell.setCellValue(co);
                    // On liste les cours pour garder l'ordre lorsque on inscrit les notes
                    coursXls.add(co);
                }
            }
            // Ajouter la colonne pour la moyenne
            cell = row.createCell(colNb++);
            cell.setCellValue("Moy.");

            // Boucle sur chaque étudiant
            for (Student stud : students.getStudents()) {
                int nbCours = 0;
                colNb = 0;

                row = sheet.createRow(++rowNb);
                cell = row.createCell(colNb++);
                cell.setCellValue(stud.getFirstname());
                cell = row.createCell(colNb++);
                cell.setCellValue(stud.getLastname());
                cell = row.createCell(colNb++);
                cell.setCellValue(stud.getAge());

                // Ajouter les notes de chaque cours
                StringBuilder moyAll = new StringBuilder(MOYENNE);
                for (String cours : coursXls) {
                    Optional<Note> noteCours = stud.getNotes().stream()
                            .filter(note -> note.getCours().equals(cours)).findAny();
                    cell = row.createCell(colNb++);
                    cell.setCellValue(noteCours.orElse(new Note()).getNote());
                    nbCours++;

                    // Il y a 4 cours par étudiant
                    moyAll.append(cell.getReference());
                    if (nbCours < 4) {
                        moyAll.append(VIRGULE);
                    }
                }
                moyAll.append(CLOSE);
                cell = row.createCell(colNb++);
                // =AVERAGEA(D3;E3;F3;G3)
                cell.setCellFormula(moyAll.toString());
            }

            // Ajouter la moyenne pour chaque cours
            row = sheet.createRow(++rowNb);
            int columnIndex = 3;

            for (int i = 0; i < (coursXls.size() + 1); i++) {
                cell = row.createCell(columnIndex++);
                StringBuilder ref = new StringBuilder(cell.getReference());
                char columnName = ref.charAt(0);
                int lineNumber = Integer.valueOf(ref.substring(1));

                // =AVERAGEA(D3:D12)
                cell.setCellFormula(MOYENNE + columnName + "3:" + columnName + (lineNumber - 1) + CLOSE);
            }

            try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
                wb.write(fileOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
