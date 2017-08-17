package com.practise;

public class WithoutMainMethod {
	static {
		System.out.println("This is a class without main method");
	}
	//Shows this error.
	/*Error: Main method not found in class com.practise.MainMethod, please define the main method as:
		   public static void main(String[] args)
		or a JavaFX application class must extend javafx.application.Application*/

}
