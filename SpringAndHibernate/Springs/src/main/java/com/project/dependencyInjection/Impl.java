package com.project.dependencyInjection;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Impl {

	public static void main(String[] args) {
		XmlBeanFactory fact = new XmlBeanFactory(new ClassPathResource(
				"applicationContextDI.xml"));
		Question question = (Question) fact.getBean("question");
		question.display();

	}

}
