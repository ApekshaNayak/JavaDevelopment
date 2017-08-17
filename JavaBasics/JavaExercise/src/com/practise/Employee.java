package com.practise;
/**
 * 
 */

/**
 * @author Apeksha
 *
 */
public class Employee {
	public String bankCode;
	static String employeeName;
	static int empId;
	int empSalary;
	String dept;
  
	public Employee() {
		bankCode = "sbi1";
		employeeName = "Apeksha";
		empId = 20151;
		empSalary = 425000;
		dept = "EAI&BPM";
	}

	public void employeeDetail(String bankCode, String employeeName,
			 int empId, int empSalary, String dept) {
		this.bankCode = bankCode;
		this.employeeName = employeeName;
		this.empId = empId;
		this.empSalary = empSalary;
		this.dept = dept;
	}
}
