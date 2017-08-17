package com.practise;

import java.sql.Date;

public class DateConverter {

	public Date converter() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

	public static void main(String[] args) {
		DateConverter dc = new DateConverter();
		System.out.println("Date: "+dc.converter());
	}

}
