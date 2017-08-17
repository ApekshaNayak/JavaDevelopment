package com.practise;
import java.util.Scanner;

public class BranchingStatements {
	public static void main(String[] args) {
		// if statement.
		int val = 51;
		if (val < 13)
			System.out.println("The value lies in the range from 0 to 12.");
		else if (val < 31)
			System.out.println("The value lies in the range from 13 to 30");
		else
			System.out.println("The value is greater than 30.\n\n\n");

		// break statement.
		/*The break statement can be used to terminate a while loop, do...while loop and a for loop in which it is used.*/
		int val1 = 1;
		int val2 = 11;
		while (val1 > 0) {
			System.out.println("Value is " + val1);
			val1++;

			if (val1 == 5) {
				break;
			} else {

				while (val2 > 10) {
					System.out.println("Value in loop2 is " + val2);
					val2++;

					if (val2 >= 15)
						break;
				}
				System.out.println("Came out of loop 2");
			}
		}
		System.out.println("Came out of the loop using break\n\n\n");
		
		//Switch statement.
		/*The most commonly used datatypes in Switch is int and string. The case values can only be constants(int or strings)*/
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a command: ");
		String command = scan.nextLine();		
		
		switch (command.toLowerCase()) {
		case "start":
			System.out.println("The program has started.");			
			break;
		case "stop":
			System.out.println("The program has stopped.");
			break;
		default:
			System.out.println("Command not recognised.");
			break;
		}
		scan.close();
		
	}
}
