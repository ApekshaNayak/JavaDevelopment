package com.mindtree.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.mindtree.Dao.EmployeeDaoImpl;
import com.mindtree.controller.Controller;
import com.mindtree.entity.Employee;

public class SaveButton implements ActionListener {

	private Controller controller;
	
	private String name;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getController() != null && getController().getFirst_name()!= null) {
			name = getController().getFirst_name().getText();
		}
		System.out.println("save button pressed......" + name);
		EmployeeDaoImpl empImpl = new EmployeeDaoImpl();
		List<Employee>employees = empImpl.retrieveEmployees();
		System.out.println("Employee List : "+employees);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}
