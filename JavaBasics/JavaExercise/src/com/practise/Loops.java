package com.practise;
import java.util.Scanner;

/**
 * @author Apeksha
 *
 */
public class Loops {

	public static void main(String[] args) {
		// Using Conditions for boolean type.
		boolean value = 6 > 1;
		System.out.println(value);

		// While loop type 1.
		int x = 0;
		while (x <= 5) {
			x++;
			System.out.printf("The value of x now is: %d\n ", x);
		}

		// while loop type 2.
		StringBuilder sb = new StringBuilder();
		sb.append("The value of y is: ");
		int y = 0;
		while (y < 6) {
			y++;
			sb.append(y).append(' ');
		}
		System.out.println(sb.toString());

		// while loop type 3.
		int z = 0;
		System.out.println("The values z: ");
		while (z < 6) {
			z++;
			System.out.printf("%14d\n", z);
		}
		
		//For loop type 1.
		/*for(;;) {
			System.out.println("Infinite loop");
		}
		*/
		//For loop type 2.
		for(int i=0;i<=5;i++) {
			System.out.println("The value of i is "+i);
		}
		
		//Do-While loop. Use this loop kind to avoid recurring statements.
		/*A program to enter a correct login key.*/
		Scanner scan = new Scanner(System.in);
		int key= 0 ;
		
		do{
			System.out.println("Please enter the Key: ");
			key = scan.nextInt();
		}
		while (key!= 78);
		System.out.println("Login successful.");
		scan.close();
	}

}
