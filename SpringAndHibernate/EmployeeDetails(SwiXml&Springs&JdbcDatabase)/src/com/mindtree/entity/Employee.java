package com.mindtree.entity;

import java.sql.Date;

public class Employee {
	String first_name;
	String last_name;
	int emp_no;
	char gender;
	Date birth_date;
	Date hire_date;
	String dept_name;
	String title;
	int salary;
	Date salary_from_date;
	Date salary_to_date;
	String dept_no;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getSalary_from_date() {
		return salary_from_date;
	}
	public void setSalary_from_date(Date salary_from_date) {
		this.salary_from_date = salary_from_date;
	}
	public Date getSalary_to_date() {
		return salary_to_date;
	}
	public void setSalary_to_date(Date salary_to_date) {
		this.salary_to_date = salary_to_date;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}


}
