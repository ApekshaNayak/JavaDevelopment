package com.practise;

public class HelloWorld {

	public static void main(String[] args) {
	
	System.out.println("HelloWorld!!!!");
	int val1 = 13;
	while (val1 > 12) {
		System.out.println("Value in loop2 is " + val1);
		val1++;

		if (val1 == 20)
			break;
	}
	System.out.println("Came out of loop 2");
	}

}
