package com.tecnotree.automatiom.utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvDataProvider {

    @Test(dataProvider = "csvData")
    public void testMethod(String data1) {
        System.out.println("Data from CSV: " + data1);
        // Your test logic here
    }
    
    @Test(dataProvider = "csvData")
    public void testMethod1(String data1) {
        System.out.println("Data from CSV: " + data1);
        // Your test logic here
    }
    
    @Test(dataProvider = "csvData")
    public void testMethod2(String data1) {
        System.out.println("Data from CSV: " + data1);
        // Your test logic here
    }

    @DataProvider(name = "csvData",indices = {1,2})
    public Object[][] readCsvData() throws IOException, CsvException {
        String filePath = "files.csv";

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> lines = csvReader.readAll();

            int rowCount = lines.size();
            int colCount = lines.get(0).length;

            Object[][] data = new Object[rowCount - 1][colCount]; // Subtract 1 for header row

            for (int i = 1; i < rowCount; i++) {
                String[] line = lines.get(i);
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = line[j];
                }
            }

            return data;
        }
    }
}
