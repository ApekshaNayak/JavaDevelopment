package com.practise;
/**
 * 
 */
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * @author M1016987
 *
 */
public class Pgm1 {
	public static void main(String args[]) throws ParseException{
		System.out.println("Employee Registering System");
		System.out.println("Enter the option you want to do:");
		System.out.println("1.Adding a new Employee\n2.Checking the details");
		int option=util.getint();

		switch(option){
		case 1:
			System.out.println("Enter the Employee details: ");
			ArrayList<String> employeeDetails1=new ArrayList<String>();
			ArrayList<String> employeeDetails11=new ArrayList<String>();
			ArrayList<Date> employeeDetails22=new ArrayList<Date>();
			ArrayList<String> employeeDetails2=new ArrayList<String>();
			ArrayList<String> employeeDetails3=new ArrayList<String>();
			ArrayList<Integer> employeeDetails33=new ArrayList<Integer>();

			employeeDetails1.add("Employee Name :");
			employeeDetails3.add("Employee ID:");
			employeeDetails1.add("Employee Education :");
			employeeDetails1.add("Place of Birth :");
			employeeDetails2.add("Date of Birth(dd/mm/yyyy) :");
			employeeDetails2.add("Date of Joining(dd/mm/yyyy) :");

			Iterator<String>iter3=employeeDetails3.iterator();
			while(iter3.hasNext()){
				System.out.println(iter3.next());
				employeeDetails33.add(util.getint());
			}

			Iterator<String> iter1=employeeDetails1.iterator();
			while(iter1.hasNext()){
				System.out.println(iter1.next());
				employeeDetails11.add(util.getstr());
			}

			Iterator<String> iter2=employeeDetails2.iterator();
			String date1=null;
			while(iter2.hasNext()){
				System.out.println(iter2.next());
				employeeDetails22.add(util.getDate(date1));
			}

			String str0=employeeDetails11.get(0);
			String str1=employeeDetails11.get(1);
			String str2=employeeDetails11.get(2);
			Date dat0=employeeDetails22.get(0);
			Date dat1=employeeDetails22.get(1);
			Integer Int=employeeDetails33.get(0);

			Jdbc.update(Int,str0,str1,str2,dat0,dat1);

			break;


		case 2:
			System.out.println("Enter the Employee name you want to check the details: ");
			String name=util.getstr();
			Jdbc.retrive(name);
			break;

		}
	}
}
