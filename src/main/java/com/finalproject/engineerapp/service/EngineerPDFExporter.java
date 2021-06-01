package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EngineerPDFExporter {
    private List<Engineer> listEngineers;

    public EngineerPDFExporter(List<Engineer> listEngineers) {
        this.listEngineers = listEngineers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.LIGHT_GRAY);

        cell.setPhrase(new Phrase("Engineer ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for(Engineer engineer : listEngineers) {
            table.addCell(String.valueOf(engineer.getId()));
            table.addCell(engineer.getFirstName());
            table.addCell(engineer.getLastName());
            table.addCell(engineer.getEmail());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.GRAY);

        Paragraph paragraph = new Paragraph("List of Engineers", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
