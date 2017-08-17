/**
 * 
 */
package com.project.springHibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.project.persist.Employee;

/**
 * @author Apeksha
 *
 */
public class EmployeeDAO {
	private HibernateTemplate hibernateTemplate;

	
	public void save(Employee e) {
		hibernateTemplate.save(e);
		System.out.println("Successful");
	}
	
	public void update(Employee e) {
		hibernateTemplate.update(e);
	}
	
	public void delete(Employee e) {
		hibernateTemplate.delete(e);
	}
	
	public void getById(int id) {
		hibernateTemplate.get(Employee.class, id);
	}
	
	public List<Employee> getEmployee() {
		List<Employee> empList = new ArrayList<Employee>();
		empList = hibernateTemplate.loadAll(Employee.class);
		return empList;
	}
	
	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * @param hibernateTemplate the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
