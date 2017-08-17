package com.practise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Television {
	private int screenSize;
	private String brand;

	/*
	 * Within an instance method or a constructor, this is a reference to the
	 * current object — the object whose method or constructor is being called.
	 * You can refer to any member of the current object from within an instance
	 * method or a constructor by using this.
	 */
	public int getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}

/* Userinput in java can be done using three methods:
 * 1. FileReader
 * 2. BufferedReader
 * 3. Scanner
 * 
 * A BufferedReader is a simple class meant to efficiently read from the
 * underling stream. Generally, each read request made of a Reader like a
 * FileReader causes a corresponding read request to be made to underlying
 * stream. Each invocation of read() or readLine() could cause bytes to be read
 * from the file, converted into characters, and then returned, which can be
 * very inefficient. Efficiency is improved appreciably if a Reader is warped in
 * a BufferedReader.
 * 
 * BufferedReader is synchronized, so read operations on a BufferedReader can
 * safely be done from multiple threads.
 * 
 * A scanner on the other hand has a lot more cheese built into it; it can do
 * all that a BufferedReader can do and at the same level of efficiency as well.
 * However, in addition a Scanner can parse the underlying stream for primitive
 * types and strings using regular expressions. It can also tokenize the
 * underlying stream with the delimiter of your choice. It can also do forward
 * scanning of the underlying stream disregarding the delimiter!
 * 
 * A scanner however is not thread safe, it has to be externally synchronized.
 * 
 * The choice of using a BufferedReader or a Scanner depends on the code you are
 * writing, if you are writing a simple log reader Buffered reader is adequate.
 * However if you are writing an XML parser Scanner is the more natural choice.
 * 
 * Even while reading the input, if want to accept user input line by line and
 * say just add it to a file, a BufferedReader is good enough. On the other hand
 * if you want to accept user input as a command with multiple options, and then
 * intend to perform different operations based on the command and options
 * specified, a Scanner will suit better.
 */
public class UserInputGettersAndSettersAndThis {
	public static void main(String[] args) {
		
		//Scanner
		Scanner input = new Scanner(System.in);

		// Inputting a binary/Hexadecimal value and displaying integer value
		// using radix.
		System.out.println("Please enter an binary number: ");
		System.out.println("You entered : " + input.nextInt(2));

		System.out.println("Please enter a hexdecimal number: ");
		System.out.println("You entered: " + input.nextInt(16));

		input.close();

		Television tv = new Television();
		tv.setBrand("Panasonic");
		tv.setScreenSize(32);

		System.out.println("The Television brand is " + tv.getBrand()
				+ "\nThe Television screen size is " + tv.getScreenSize());
		
		//BufferedReader
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
	}
	

}
