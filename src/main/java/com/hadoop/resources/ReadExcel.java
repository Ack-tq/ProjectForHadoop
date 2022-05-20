package com.hadoop.resources;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcel {

	@Test
	public void readExcel03() throws IOException {
		String path = "E:\\hadoop\\Project4H\\ProjectForHadoop\\src\\";
		String propertiesFile = "test03.xls";
		FileInputStream fileInputStream = new FileInputStream(path + propertiesFile);
		Workbook workbook = new HSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		System.out.println(cell.getStringCellValue());
		fileInputStream.close();
	}

	@Test
	public void readExcel07() throws IOException {
		String path = "E:\\hadoop\\Project4H\\ProjectForHadoop\\src\\";
		String propertiesFile = "test07.xlsx";
		FileInputStream fileInputStream = new FileInputStream(path + propertiesFile);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(1);
		System.out.println(cell.getStringCellValue());
		fileInputStream.close();
	}
}
