package com.mindtree.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

import com.mindtree.entity.Employee;
import com.mindtree.listener.SaveButton;

public class Controller {
	String layout;
	String firstName = null;
	private JTextField first_name;
	private JButton save;
	private SaveButton savebuttonListener;

	public String getLayout() {
		try {
			new SwingEngine(this).render("employeeForm.xml").setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return layout;
	}

	public void initializeButtons() {
		savebuttonListener.setController(this);
		save.addActionListener(savebuttonListener);
	}

	public void printEmployee(Employee emp) {
		System.out.println("Employee: " + emp.getGender() + " "
				+ emp.getFirst_name() + " " + emp.getLast_name() + " "
				+ emp.getBirth_date() + " " + emp.getHire_date());
	}
	
	public java.sql.Date mySqlDateConverter(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date parseDate = format.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(parseDate.getTime());
		System.out.println("new date : "+ sqlDate);
		return sqlDate;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setFirst_name(JTextField first_name) {
		this.first_name = first_name;
	}

	public JTextField getFirst_name() {
		return first_name;
	}

	public JButton getSave() {
		return save;
	}

	public void setSave(JButton save) {
		this.save = save;
	}

	public SaveButton getSavebuttonListener() {
		return savebuttonListener;
	}

	public void setSavebuttonListener(SaveButton savebuttonListener) {
		this.savebuttonListener = savebuttonListener;
	}
}
