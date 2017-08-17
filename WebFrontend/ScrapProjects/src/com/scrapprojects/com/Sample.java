package com.scrapprojects.com;

import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Sample {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String val = scan.next();
		String str = Integer.toBinaryString(Integer.parseInt(val));
		System.out.println("Integer:" +val);
		System.out.println("Binary of Integer:" +str);
		String newStr= "";
		
		for(int i=0;i<str.length();i++){
			if(str.charAt(i) == '0') {
				newStr+='1';
			}
			else if(str.charAt(i) == '1') {
				newStr+='0';
			}
		}
		System.out.println("New Integer"+BinaryOperator.(newStr));
		System.out.println("Binary value of the new Integer"+newStr);
	}

}
