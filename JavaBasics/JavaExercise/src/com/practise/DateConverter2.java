package com.practise;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter2 {
	public Date string2Date() {
		String testDate = "29-Apr-2010,13:00:14 PM";
		DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
		Date date1 = null;
		try {
			date1 = formatter.parse(testDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date1;
		}
	public static void main(String[] args) {

		DateConverter2 scr = new DateConverter2();
		System.out.println("Date : "+ scr.string2Date());
	}

}
