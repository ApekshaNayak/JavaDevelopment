package com.project.settermethod;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class SIImpl {

	public static void main(String[] args) {
		XmlBeanFactory fact = new XmlBeanFactory(new ClassPathResource(
				"settermethod.xml"));
		Employee emp = (Employee) fact.getBean("employee");
		System.out.println(emp.toString());
	}

}
