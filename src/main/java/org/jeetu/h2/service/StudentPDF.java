package org.jeetu.h2.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.jeetu.h2.model.Student;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class StudentPDF {
	public ByteArrayInputStream downloadPDF(List<Student> students) {
		String[] HEADER = { "ID", "First Name", "Last Name", "Email", "Phone" };
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			Paragraph para = new Paragraph("Student Table");
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(5);
			Stream.of(HEADER).forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(1);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});
			for (Student student : students) {
				PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(student.getId())));
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell firstNameCell = new PdfPCell(new Phrase(String.valueOf(student.getFirst())));
				firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(firstNameCell);

				PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(student.getLast())));
				lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(lastNameCell);

				PdfPCell emailCell = new PdfPCell(new Phrase(String.valueOf(student.getEmail())));
				emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(emailCell);

				PdfPCell phoneCell = new PdfPCell(new Phrase(String.valueOf(student.getPhone())));
				phoneCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(phoneCell);
			}
			document.add(table);
			document.close();
			writer.close();
		} catch (DocumentException e) {
			System.out.println(e);
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
