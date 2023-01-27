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

    private static final int TABLE_COLUMNS = 4;
    private static final String TITLE_FONT = FontFactory.HELVETICA_BOLD;
    private static final String HEADER_FONT = FontFactory.HELVETICA;
    private static final Color TITLE_FONT_COLOR = Color.GRAY;
    private static final Color HEADER_FONT_COLOR = Color.BLACK;
    private static final Color HEADER_COLOR = Color.LIGHT_GRAY;
    private static final String TITLE = "List of Engineers";
    private static final String HEADER_COLUMN_1 = "Engineer ID";
    private static final String HEADER_COLUMN_2 = "First name";
    private static final String HEADER_COLUMN_3 = "Last name";
    private static final String HEADER_COLUMN_4 = "E-mail";


    private List<Engineer> listEngineers;

    public EngineerPDFExporter(List<Engineer> listEngineers) {
        this.listEngineers = listEngineers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = getHeaderCell();
        Font font = getHeaderFont();
        setColumnsHeaders(table, cell, font);
    }

    private static PdfPCell getHeaderCell() {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(HEADER_COLOR);
        cell.setPadding(5);
        return cell;
    }

    private static Font getHeaderFont() {
        Font font = FontFactory.getFont(HEADER_FONT);
        font.setColor(HEADER_FONT_COLOR);
        return font;
    }

    private static void setColumnsHeaders(PdfPTable table, PdfPCell cell, Font font) {
        cell.setPhrase(new Phrase(HEADER_COLUMN_1, font));
        table.addCell(cell);

        cell.setPhrase(new Phrase(HEADER_COLUMN_2, font));
        table.addCell(cell);

        cell.setPhrase(new Phrase(HEADER_COLUMN_3, font));
        table.addCell(cell);

        cell.setPhrase(new Phrase(HEADER_COLUMN_4, font));
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

    public void exportPDF(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.add(getTitle());
        document.add(getTable());
        document.close();
    }

    private PdfPTable getTable() {
        PdfPTable table = createTable();
        writeTableHeader(table);
        writeTableData(table);
        return table;
    }

    private static PdfPTable createTable() {
        PdfPTable table = new PdfPTable(TABLE_COLUMNS);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
        return table;
    }

    private static Paragraph getTitle() {
        Font font = FontFactory.getFont(TITLE_FONT);
        font.setSize(18);
        font.setColor(TITLE_FONT_COLOR);

        Paragraph title = new Paragraph(TITLE, font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        return title;
    }
}
