package com.mindtree.Dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mindtree.entity.Employee;
import com.mindtree.interfaces.EmployeeDao;
import com.mindtree.mapper.EmployeeMapper;

public class EmployeeDaoImpl implements EmployeeDao {
	// private DataSource dataSource;

	private JdbcTemplate jdbcTemplete;
	private EmployeeMapper empMapper;

	public JdbcTemplate getJdbcTemplete() {
		return jdbcTemplete;
	}

	public void setJdbcTemplete(JdbcTemplate jdbcTemplete) {
		this.jdbcTemplete = jdbcTemplete;
	}

	@Override
	public void createEmployee(String first_name, String last_name,
			String gender, Date birth_date, Date hire_date) {
		String createEmp = "insert into Employee (first_name,last_name,gender,birth_date,hire_date)values (?, ?, ?, ?, ?)";
		
		this.getJdbcTemplete().update(createEmp, first_name, last_name, gender,
				birth_date, hire_date);
	}

	@Override
	public void updateEmployee(String first_name, String last_name, int emp_no,
			char gender, Date birth_date, Date hire_date, String dept_name,
			String title, int salary, Date salary_from_date, Date salary_to_date) {

	}

	@Override
	public void deleteEmployee(int emp_no) {

	}

	@Override
	public List<Employee> retrieveEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		String sqlEmp = "select *from employee where first_name ='Abhishek'";
		empList = this.getJdbcTemplete().query(sqlEmp, new EmployeeMapper());
		return empList;
	}


}
