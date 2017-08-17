package com.project.Springs;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Hello world!
 *
 */
public class SpringImpl {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("applicationContext.xml");
		BeanFactory fact = new XmlBeanFactory(resource);

		// Dependency injection by Dependent objects.
		EmployeeData emp = (EmployeeData) fact.getBean("empl2");
		emp.display();

		// Dependency injection by Collection List values.
		Questions questions = (Questions) fact.getBean("quest");
		questions.show();

		// Dependency injection by Collection List object values.
		Question quest = (Question) fact.getBean("question");
		quest.showList();

		// Dependency injection by Collection Map object values.
		QuestionMap questMap = (QuestionMap) fact.getBean("questionMap");
		questMap.displayInfo();
	}
}
