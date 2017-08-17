/**
 * 
 */
package com.project.annotation;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Apeksha
 *
 */
public class EmployeeClass {
	private String name;
	private String id;
	
	@Autowired
	AddressClass address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddressClass getAddress() {
		return address;
	}

	public void setAddress(AddressClass address) {
		this.address = address;
	}
}
