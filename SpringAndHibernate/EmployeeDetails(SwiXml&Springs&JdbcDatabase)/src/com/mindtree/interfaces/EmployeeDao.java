package com.mindtree.interfaces;

import java.sql.Date;
import java.util.List;

import com.mindtree.entity.Employee;

public interface EmployeeDao {
	
	//void setDataSource(DataSource ds) ;
	void createEmployee(String first_name, String last_name,
			String gender,  Date birth_date, Date hire_date );

	void updateEmployee(String first_name, String last_name, int emp_no,
			char gender, Date birth_date, Date hire_date, String dept_name,
			String title, int salary, Date salary_from_date, Date salary_to_date);

	void deleteEmployee(int emp_no);
	
	List<Employee> retrieveEmployees();


}
