/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.KSS.Entities;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import static org.apache.poi.ss.util.CellUtil.createCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class ExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelExporter() {
        workbook = new XSSFWorkbook();
    }

private void writeHeaderLine(){
    sheet = workbook.createSheet("Report cardd");

    Row row = sheet.createRow(0);

    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setBold(true);
    font.setFontHeight(16);
    style.setFont(font);

    createCell(row, 0, "User ID", style);
    createCell(row, 1, "E-mail", style);
    createCell(row, 2, "Full Name", style);
    createCell(row, 3, "Roles", style);
    createCell(row, 4, "Enabled", style);
}

//    public void export(HttpServletResponse response) throws IOException {
//        writeHeaderLine();
//        writeDataLines();
//
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        outputStream.close();
//
//    }

}
