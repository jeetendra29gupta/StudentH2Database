package org.jeetu.h2.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeetu.h2.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentExcel {
	@SuppressWarnings("resource")
	public ByteArrayInputStream downloadExcel(List<Student> students) {
		String[] HEADER = { "ID", "First Name", "Last Name", "Email", "Phone" };
		Workbook workbook = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			Sheet sheet = workbook.createSheet("Student");
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADER.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADER[col]);
			}
			int rowIdx = 1;
			for (Student student : students) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(String.valueOf(student.getId()));
				row.createCell(1).setCellValue(String.valueOf(student.getFirst()));
				row.createCell(2).setCellValue(String.valueOf(student.getLast()));
				row.createCell(3).setCellValue(String.valueOf(student.getEmail()));
				row.createCell(4).setCellValue(String.valueOf(student.getPhone()));

				workbook.write(out);
				
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
