/**
 * 
 */
package com.project.annotation;

import org.springframework.stereotype.Service;

/**
 * @author Apeksha
 *
 */
@Service
public class AddressClass {
	private String street;
	private String country;

	public AddressClass() {
		this.street = "memorial lane";
		this.country = "USA";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
