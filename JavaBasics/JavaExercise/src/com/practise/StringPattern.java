package com.practise;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */

public class StringPattern {
	public static void main(String[] args) {
		String pattern1 = "(\\w)*";
		String pattern2 = "^Apeksha";
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the String : ");
		String temp = scan.next();

		

		if (temp.matches(pattern1)) {
			System.out.println("The match found is " + temp.toString()); // prints
																			// /{item}/
		} else {
			System.out.println("Match not found");
		}
	}
}
