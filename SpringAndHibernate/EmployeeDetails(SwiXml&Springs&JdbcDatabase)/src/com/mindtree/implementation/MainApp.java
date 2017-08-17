package com.mindtree.implementation;

import java.text.ParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mindtree.Dao.EmployeeDaoImpl;
import com.mindtree.controller.Controller;
import com.mindtree.entity.Employee;

public class MainApp {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		Controller controller = (Controller) context.getBean("controller");
		/*controller.getLayout();
		controller.initializeButtons();
*/
		String birthDate = "29-10-1989";
		String hireDate = "11-08-2011";
		String firstName = "Apeksha";
		String lastName = "Nayak";
		String gender = "F";

		EmployeeDaoImpl empImpl = (EmployeeDaoImpl) context
				.getBean("employeeDaoImpl");
		empImpl.createEmployee(firstName, lastName, gender,
				controller.mySqlDateConverter(birthDate),
				controller.mySqlDateConverter(hireDate));
		List<Employee> employees = empImpl.retrieveEmployees();
		for (Employee emp : employees) {
			controller.printEmployee(emp);
		}

	}

}
