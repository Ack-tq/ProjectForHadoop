package com.hadoop.resources;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcel {

	@Test
	public void writeExcel03() throws IOException {
		//工作簿
		Workbook workbook = new HSSFWorkbook();
		//工作表
		Sheet sheet = workbook.createSheet("学生信息表");
		//创建一个行
		Row row = sheet.createRow(0);
		//创建一个cell
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("姓名03");
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("学号03");
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("密码03");
		FileOutputStream fileOutputStream = new FileOutputStream("E:\\hadoop\\Project4H\\ProjectForHadoop\\src\\" + "test03.xls");
		workbook.write(fileOutputStream);
		fileOutputStream.close();
	}

	@Test
	public void writeExcel07() throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("姓名07");
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("学号07");
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("密码07");

		FileOutputStream fileOutputStream = new FileOutputStream("E:\\hadoop\\Project4H\\ProjectForHadoop\\src\\" + "test07.xlsx");
		workbook.write(fileOutputStream);
		fileOutputStream.close();
	}
}
