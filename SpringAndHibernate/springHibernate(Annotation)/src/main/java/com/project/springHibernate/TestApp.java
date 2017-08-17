package com.project.springHibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.persist.Employee;

public class TestApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeDAO empD = (EmployeeDAO) context.getBean("employeeDAO");
		
		Employee emp = new Employee();
		emp.setId(127);
		emp.setName("Sweetyi");
		emp.setSalary(770560);
		
		empD.save(emp);

	}

}
