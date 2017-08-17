package com.project.autowiring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Test {

	public static void main(String[] args) {
		XmlBeanFactory fact = new XmlBeanFactory(new ClassPathResource(
				"applicationContextAutowiring.xml"));
		A a= (A) fact.getBean("a");
		a.show();

	}

}
