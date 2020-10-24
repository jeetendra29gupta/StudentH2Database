package org.jeetu.h2.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.jeetu.h2.model.Student;
import org.jeetu.h2.service.StudentExcel;
import org.jeetu.h2.service.StudentPDF;
import org.jeetu.h2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	@Autowired
	StudentService service;

	@GetMapping("/")
	public ModelAndView main() {
		List<Student> students = service.getAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("students", students);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		System.out.println();
		Student student = service.getStudent(id);
		model.addAttribute("student", student);
		return "update";
	}

	@GetMapping("/add")
	public String showForm(Student student) {
		return "add";
	}

	@PostMapping("/save")
	public String processForm(Student student) {
		service.save(student);
		return "redirect:/";
	}

	@PostMapping("/updating/{id}")
	public String updateStudent(@PathVariable("id") int id, Student student) {
		student.setId(id);
		service.save(student);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") int id) {
		service.delete(id);
		return "redirect:/";
	}

	@Autowired
	StudentPDF pdf;

	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> getPDF() throws IOException {
		List<Student> students = service.getAll();
		ByteArrayInputStream bis = pdf.downloadPDF(students);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=Student.pdf");
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.parseMediaType("application/pdf"))
				.body(new InputStreamResource(bis));
	}
	
	@Autowired
	StudentExcel excel;

	@GetMapping("/excel")
	public ResponseEntity<InputStreamResource> getExcel() throws IOException {
		List<Student> students = service.getAll();
		ByteArrayInputStream bis = excel.downloadExcel(students);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Student.xlsx");
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(new InputStreamResource(bis));
	}

}
