package ch.hegarc.ig.util.StatsUtils;

import ch.hegarc.ig.cpo.business.Student;
import ch.hegarc.ig.cpo.business.StudentList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.OptionalDouble;

public class MainPdf {

    public static void main(String[] args) throws Exception {

        writePdf();
        readPdf();
    }

    static void readPdf() {
        File myFile = new File("Serie 14.pdf");

        try (PDDocument doc = PDDocument.load(myFile)) {

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);

            System.out.println("Text size: " + text.length() + " characters:");
            System.out.println(text);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static void writePdf() {
        ObjectMapper om = new ObjectMapper();
        String outputFileName = "Moyennes.pdf";
        try {
            StudentList students = om.readValue(new File("StudentsNotes.json"), StudentList.class);

            // Create a document and add a page to it
            PDDocument document = new PDDocument();
            PDPage page1 = new PDPage(PDRectangle.A4);
            PDRectangle rect = page1.getMediaBox();
            document.addPage(page1);

            // Create a new font object selecting one of the PDF base fonts
            PDFont fontPlain = PDType1Font.HELVETICA;
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;

            // Start a new content stream which will hold the content that's about to be created
            PDPageContentStream cos = new PDPageContentStream(document, page1);

            // Define a text content stream using the selected font, move the cursor and draw some text
            cos.beginText();
            cos.setFont(fontBold, 14);
            // Titre plus ou moins centré
            cos.newLineAtOffset(rect.getWidth() / 2 - 20, rect.getHeight() - 100);
            cos.showText("Moyennes");
            cos.endText();

            cos.setLeading(20f);
            cos.setFont(fontPlain, 14);
            cos.beginText();
            cos.newLineAtOffset(100, 600);

            for (Student stud : students.getStudents()){
                String prenom = stud.getFirstname();
                String nom = stud.getLastname();
                OptionalDouble moy = stud.getNotes().stream().mapToDouble(note -> note.getNote()).average();
                BigDecimal moyBig = new BigDecimal(moy.getAsDouble());
                cos.showText(prenom + " " + nom + " : " + moyBig.setScale(2, RoundingMode.HALF_UP) );
                cos.newLine();
            }

            cos.endText();
            cos.close();

            document.save(outputFileName);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}