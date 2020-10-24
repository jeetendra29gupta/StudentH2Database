package org.jeetu.h2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private int id;
	private String first;
	private String last;
	private String email;
	private String phone;
	private String message;

	public Student() {
	}

	public Student(String first, String last, String email, String phone, String message) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.phone = phone;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", first=" + first + ", last=" + last + ", email=" + email + ", phone=" + phone
				+ ", message=" + message + "]";
	}
}
