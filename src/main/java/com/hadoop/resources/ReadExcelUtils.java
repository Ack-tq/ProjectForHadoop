package com.hadoop.resources;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadExcelUtils {
	static String Path = null;
	static String FileName = null;
	static FileInputStream fileInputStream = null;
	static Workbook workbook;
	static Sheet sheet;

	static {
		Properties properties = new Properties();
		InputStream resourceAsStream = null;
		try {
			resourceAsStream = new FileInputStream("E:\\hadoop\\Project4H\\ProjectForHadoop\\src\\main\\resources\\ExcelPath.properties");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			properties.load(resourceAsStream);
			Path = properties.getProperty("path");
			FileName = properties.getProperty("filename");
			fileInputStream = new FileInputStream(Path + FileName);
			String[] split = FileName.split("\\.");
			if (split[1].equals("xls")) {
				workbook = new HSSFWorkbook(fileInputStream);
			} else if (split[1].equals("xlsx")) {
				workbook = new XSSFWorkbook(fileInputStream);
			} else {
				System.err.println("ON HERE!");
				throw new Exception("FileTypeException:This is not the target format file(xls, xlsx).");
			}
			sheet = getSheet(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resourceAsStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static Sheet getSheet(int i) {
		if (workbook == null) {
			System.err.println("workbook is null");
			return null;
		}
		return workbook.getSheetAt(i);
	}

	public static Sheet getSheet(String s) {
		return workbook.getSheet(s);
	}

	public static String[] getTitles() {
		return getEveryCellsInRow(0);
	}

	public static int getRowSize() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public static String[] getEveryCellsInRow(int i) {
		Row row = sheet.getRow(i);
		String[] cellsString = new String[row.getPhysicalNumberOfCells()];
		for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
			CellType cellType = row.getCell(k).getCellType();
			switch (cellType){
				case NUMERIC :
					cellsString[k] = String.valueOf(row.getCell(k).getNumericCellValue());
					break;
				case STRING:
					cellsString[k] = row.getCell(k).getStringCellValue();
					break;
			}
		}
		return cellsString;
	}
/*
	public static void releaseFileReader(FileInputStream fileInputStream) throws IOException {
		if (fileInputStream != null) fileInputStream.close();
	}*/

	public static void releaseRes() throws IOException {
		if (workbook != null) workbook.close();
		if (fileInputStream != null) fileInputStream.close();
	}

	//获取excel表格的行数，返回最后一行的行号，而第一行是从0开始的，一般作为表的标题栏
	public static int getRows() {
		return sheet.getLastRowNum();
	}
	@Test
	public void test() {
		for (String title : ReadExcelUtils.getTitles()) {
			System.out.println(title);
		}
	}
}
