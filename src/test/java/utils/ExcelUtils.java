package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static Workbook wb;
    public static Sheet s;
    public static Row r;
    public static Cell c;



    public static synchronized int getRowCount(String filePath,String sheet) throws IOException {
        fis = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fis);
        s = wb.getSheet(sheet);
        return s.getLastRowNum();
    }

     public static synchronized int getCellCount(String filePath, String sheet, int row) throws IOException {
        fis = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fis);
        s = wb.getSheet(sheet);
        r = s.getRow(row);
        return r.getLastCellNum();
     }

     public static synchronized String getCellData(String filePath,String sheet,int row,int cell) throws IOException {
        String cellData="";
        fis = new FileInputStream(filePath);
        wb= new XSSFWorkbook(fis);
        s = wb.getSheet(sheet);
        r = s.getRow(row);
        c = r.getCell(cell);
        try{
            cellData = c.getStringCellValue();
        } catch (Exception e){
            cellData = "";
        }
        wb.close();
        fis.close();
        return  cellData;
     }

     public static synchronized void setCellData(String filePath,String sheet,int row,int cell,String data) throws IOException {
        fis = new FileInputStream(filePath);
        wb= new XSSFWorkbook(fis);
        s = wb.getSheet(sheet);
        r = s.getRow(row);
        c = r.createCell(cell);
        c.setCellValue(data);
        fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
     }

    public static synchronized void fillGreenColor(String filePath, String sheet, int row, int cell) throws IOException {
        fis = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fis);
        s = wb.getSheet(sheet);
        r = s.getRow(row);
        if (r == null) {
            r = s.createRow(row);
        }
        c = r.getCell(cell);
        if (c == null) {
            c = r.createCell(cell);
        }

        // Create a cell style with green background
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        c.setCellStyle(cellStyle);

        fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }

    public static synchronized void fillRedColor(String filePath, String sheet, int row, int cell) throws IOException {
        fis = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fis);
        s = wb.getSheet(sheet);
        r = s.getRow(row);
        if (r == null) {
            r = s.createRow(row);
        }
        c = r.getCell(cell);
        if (c == null) {
            c = r.createCell(cell);
        }

        // Create a cell style with red background
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        c.setCellStyle(cellStyle);

        fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }

    public static synchronized void setCellDataByCreatingRow(String filePath,String sheet,int row,int cell,String data) throws IOException {
        fis = new FileInputStream(filePath);
        wb= new XSSFWorkbook(fis);
        s = wb.getSheet(sheet);
        r = s.createRow(row);
        c = r.createCell(cell);
        c.setCellValue(data);
        fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }

}
