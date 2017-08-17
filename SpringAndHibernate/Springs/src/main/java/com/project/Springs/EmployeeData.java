package com.project.Springs;

public class EmployeeData {
	private int id;
	private String name;
	private Address addr;

	public EmployeeData(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public EmployeeData(int id) {
		this.id = id;
	}

	public EmployeeData(String name) {
		this.name = name;
	}
	public EmployeeData(int id, String name, Address addr) {
		this.id = id;
		this.name = name;
		this.addr = addr;
	}
	
	public void display() {
		System.out.println("Employee Id: "+id+"\nEmployee Name: "+name+"\nEmployee address: "+addr.toString());
	}
}
