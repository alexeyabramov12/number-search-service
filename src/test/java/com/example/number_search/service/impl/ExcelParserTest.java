package com.example.number_search.service.impl;

import com.example.number_search.service.FileParser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExcelParserTest {

    private FileParser excelParser;

    @BeforeEach
    void setUp() {
        excelParser = new ExcelParser();
    }

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Should correctly parse numbers from a single-sheet Excel file")
    void givenValidExcelFile_whenParse_thenReturnNumbers() throws IOException {
        File excelFile = tempDir.resolve("test.xlsx").toFile();
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(excelFile)) {

            Sheet sheet = workbook.createSheet("Sheet1");
            createNumericRow(sheet, 0, 10);
            createNumericRow(sheet, 1, 20);
            createNumericRow(sheet, 2, 30);
            workbook.write(fos);
        }

        List<Integer> result = excelParser.parse(excelFile.getAbsolutePath());

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(List.of(10, 20, 30), result);
    }

    @Test
    @DisplayName("Should parse numbers from multiple sheets correctly")
    void givenMultiSheetExcelFile_whenParse_thenReturnAllNumbers() throws IOException {
        File excelFile = tempDir.resolve("multi-sheet.xlsx").toFile();
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(excelFile)) {

            Sheet sheet1 = workbook.createSheet("Sheet1");
            createNumericRow(sheet1, 0, 5);
            createNumericRow(sheet1, 1, 15);

            Sheet sheet2 = workbook.createSheet("Sheet2");
            createNumericRow(sheet2, 0, 25);
            createNumericRow(sheet2, 1, 35);

            workbook.write(fos);
        }

        List<Integer> result = excelParser.parse(excelFile.getAbsolutePath());

        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals(List.of(5, 15, 25, 35), result);
    }

    @Test
    @DisplayName("Should return an empty list if the Excel file has no numbers")
    void givenEmptyExcelFile_whenParse_thenReturnEmptyList() throws IOException {
        File excelFile = tempDir.resolve("empty.xlsx").toFile();
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(excelFile)) {
            workbook.createSheet("Sheet1");
            workbook.write(fos);
        }

        List<Integer> result = excelParser.parse(excelFile.getAbsolutePath());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should throw RuntimeException when file does not exist")
    void givenNonExistentFile_whenParse_thenThrowException() {
        String invalidPath = tempDir.resolve("missing.xlsx").toString();

        Exception exception = assertThrows(RuntimeException.class, () -> excelParser.parse(invalidPath));

        assertTrue(exception.getMessage().contains("Error reading Excel file"));
    }

    // Helper method to create a row with a numeric value in column A (index 0)
    private void createNumericRow(Sheet sheet, int rowIndex, int value) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue(value);
    }
}
