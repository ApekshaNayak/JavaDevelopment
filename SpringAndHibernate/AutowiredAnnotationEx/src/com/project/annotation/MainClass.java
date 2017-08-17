/**
 * 
 */
package com.project.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Apeksha
 *
 */
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"bean.xml");
		EmployeeClass emp = (EmployeeClass) context.getBean("emp");
		emp.setId("M10167");
		emp.setName("Soonu");
		System.out.println("Name: "+emp.getName());
		System.out.println("Id: "+emp.getId());
		System.out.println("Address: "+emp.getAddress().getCountry());
	}
}
