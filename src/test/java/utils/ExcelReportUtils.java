package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.cucumber.java.Scenario;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReportUtils {
    private Workbook workbook;
    private Sheet sheet;
    private int rowCount = 1;

    public ExcelReportUtils() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("TestReport");
    }

    public synchronized void createHeader() {
        Row headerRow = sheet.createRow(0);
        Cell headerCell0 = headerRow.createCell(0);
        headerCell0.setCellValue("Scenario ID");
        Cell headerCell1 = headerRow.createCell(1);
        headerCell1.setCellValue("Scenario Name");
        Cell headerCell2 = headerRow.createCell(2);
        headerCell2.setCellValue("Item Name");
        Cell headerCell3 = headerRow.createCell(3);
        headerCell3.setCellValue("First Name");
        Cell headerCell4 = headerRow.createCell(4);
        headerCell4.setCellValue("Last Name");
        Cell headerCell5 = headerRow.createCell(5);
        headerCell5.setCellValue("Postal Code");
        Cell headerCell6 = headerRow.createCell(6);
        headerCell6.setCellValue("Status");
    }

    // Add scenario name to Excel
    public synchronized void logScenario(Scenario scenario) throws IOException {

        // Create a new row for the scenario
        Row row = sheet.createRow(rowCount++);

        Cell cell0 = row.createCell(0); // zeroth column
        cell0.setCellValue(getScenarioId(scenario));

        Cell cell1 = row.createCell(1); // first column
        cell1.setCellValue(scenario.getName()); // scenario name

        Cell cell2 = row.createCell(2);
        String itemName = getDataFromExistingTestDataExcelFile(scenario,1);
        cell2.setCellValue(itemName);// second column

        Cell cell3 = row.createCell(3);
        String firstName = getDataFromExistingTestDataExcelFile(scenario,2);
        cell3.setCellValue(firstName);// third column

        Cell cell4 = row.createCell(4);
        String lastName = getDataFromExistingTestDataExcelFile(scenario,3);
        cell4.setCellValue(lastName);// fourth column

        Cell cell5 = row.createCell(5);
        String postalCode = getDataFromExistingTestDataExcelFile(scenario,4);
        cell5.setCellValue(postalCode);// fifth column

        Cell cell6 = row.createCell(6);
        if (scenario.isFailed()) {
            cell6.setCellValue("FAILED");
            cellColor(cell6, "RED");
        } else {
            cell6.setCellValue("PASSED");
            cellColor(cell6, "GREEN");
        }

    }

    public synchronized void cellColor(Cell cell, String color) {
        CellStyle style = workbook.createCellStyle();
        if (color.equalsIgnoreCase("GREEN")) {
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        } else if (color.equalsIgnoreCase("RED")) {
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
        } else {
            style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        }
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
    }

    public synchronized String getDataFromExistingTestDataExcelFile(Scenario scenario,int cellNum) throws IOException {

        //Row Count from ExcelUtils to get data from existing testData.xlsx file
        int rowCountExcelUtils = ExcelUtils.getRowCount("src/test/resources/testData.xlsx", "Sheet1");

        String cellData="";

        for(int i=0; i<=rowCountExcelUtils; i++) {
            String testCaseId = ExcelUtils.getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 0); // 1st column contains ScenarioIds
            String scenarioId = getScenarioId(scenario);
            if (scenarioId != null && scenarioId.equals(testCaseId)) {
                cellData = ExcelUtils.getCellData("src/test/resources/testData.xlsx", "Sheet1", i, cellNum); // 2nd column contains ItemNames
                break; // Exit loop once the matching Scenario ID is found
            }
        }
        return cellData;
    }

    public  String getScenarioId(Scenario scenario) {
        return scenario.getSourceTagNames()
                .stream()
                .filter(tag -> tag.startsWith("@ScenarioId="))
                .map(tag -> tag.replace("@ScenarioId=", ""))
                .findFirst()
                .orElse(null);
    }

    // Save the Excel file
    public synchronized void saveReport(String filePath) {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}