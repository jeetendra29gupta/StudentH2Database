package org.jeetu.h2.service;

import java.util.ArrayList;
import java.util.List;

import org.jeetu.h2.model.Student;
import org.jeetu.h2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	StudentRepository repository;

	// Save student entity in the h2 database.
	public void save(Student student) {
		repository.save(student);
	}

	// Get all students from the h2 database.
	public List<Student> getAll() {
		List<Student> students = new ArrayList<Student>();
		repository.findAll().forEach(student -> students.add(student));
		return students;
	}
	
	// Delete student with given ID from the h2 database.
	public void delete(int id) {
		repository.deleteById(id);
	}
	
	public Student getStudent(int id) {
		return repository.findById(id).get();
	}
}
