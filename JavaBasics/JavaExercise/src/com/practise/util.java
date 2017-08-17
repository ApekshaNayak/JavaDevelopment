package com.practise;
/**
 * 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author M1016987
 *
 */
public class util {
	public static int getint(){
		Scanner scan=new Scanner(System.in);
		return scan.nextInt();
	}
	
	public static String getstr(){
		Scanner scan=new Scanner(System.in);
		return scan.next();
	}
	
	/*public static Date getDate(String date1){
		Scanner scan=new Scanner(System.in);
		date1=scan.next();
		Date date=new Date();
		try {	
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
				 date=sdf.parse(date1);
				 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return date;
	
		}*/
	
	public static java.sql.Date getDate(String date1){
		Scanner scan=new Scanner(System.in);
		date1=scan.next();
		Date date=new Date();
		java.sql.Date sqlDate=null;
		try {	
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				 date=sdf.parse(date1);
				 sqlDate=new java.sql.Date(date.getTime());
				 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return (sqlDate);
	}
	
}	

	

