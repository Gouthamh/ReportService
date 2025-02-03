package com.tecnotree.automatiom.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProviderTest {

    public static Object[][] readExcelData(String pathName, String sheetName) throws IOException {
        Object[][] data = null;
        File excelFile = new File(pathName);
        
        if (excelFile.exists()) {
            FileInputStream fis = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[noOfRows - 1][noOfCols];

            for (int i = 1; i < noOfRows; i++) {
                XSSFRow row = sheet.getRow(i);

                for (int j = 0; j < noOfCols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = getCellValue(cell);
                }
            }

            workbook.close();
            fis.close();

            return data;
        } else {
            throw new IOException("File not found: " + pathName);
        }
    }

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return cell.getStringCellValue();
        }
    }

    // This method seems unrelated to Excel data provider, consider removing it
    public void search(Object keyWord1, Object keyWord2) {
        System.out.println("keyWord1 :" + keyWord1 + "  " + "keyWord1  :" + keyWord2);
    }
    
    @DataProvider(name = "d")
    public Object[][] name() throws IOException {
    	return readExcelData("Test.xlsx","Sheet1");
	}
    
    @Test(dataProvider = "d")
    private void publ(String a,String b,String c) {
		// TODO Auto-generated method stub
System.out.println(a +"--"+ b +"--"+c);
	}
}
