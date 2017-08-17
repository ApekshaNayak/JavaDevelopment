package com.mindtree.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindtree.entity.Employee;

public class EmployeeMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowno) throws SQLException {
		Employee employee =  new Employee();
		employee.setFirst_name(rs.getString("first_name"));
		employee.setLast_name(rs.getString("last_name"));
		employee.setBirth_date(rs.getDate("birth_date"));
		employee.setHire_date(rs.getDate("hire_date"));
		employee.setGender(rs.getString("gender").charAt(0));
		return employee;
	}

}
