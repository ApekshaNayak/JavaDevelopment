package com.practise;
public class Strings {

	public static void main(String[] args) {
		// String is a non-primitive datatype.
		// String Concatenation.
		// Type 1.
		String text = "Hello";
		String space = " ";
		String name = "Bob";
		String greeting = text + space + name;
		System.out.println(greeting);

		// Type 2.
		System.out.println("Hello" + " " + "Carl");

		// Inefficient type.
		String info = "";
		info += "I am Bob.";
		info += " ";
		info += "I am a builder.";
		System.out.println(info);

		// More efficient using String Builder/String Buffer.
		/*
		 * String Buffer is a thread safe version of String builder. Both are
		 * exactly the same. String buffer is the older version.
		 * 
		 * String builder objects are like string objects except that they can be modified.
		 */
		// Type 1.
		StringBuilder sb = new StringBuilder();
		sb.append("I am Apeksha.");
		sb.append(" ");
		sb.append("I am a Java Developer.");
		/*
		 * String builder return a object of the type String builder hence to
		 * print the string we need to convert the object type to the String
		 * type.
		 */
		System.out.println(sb.toString());

		// Type 2.
		StringBuilder sb2 = new StringBuilder();
		sb2.append("I am Apeksha.").append(" ").append("I am 26 years old.");
		System.out.println(sb2.toString());

		// FORMATTING.
		// Outputting new tabs and new lines.
		System.out
				.print("Here is some text.\tThat was a tab.\nThat was a new line. ");
		System.out.println("More text");

		// Formatting String.
		System.out
				.printf("The formatted String is: %s Thanks\n", "I am Roger.");
		System.out.printf("The formatted String is: %20s Thanks\n",
				"I am Roger.");
		System.out.printf("The formatted String is: %-20s Thanks\n",
				"I am Roger.");
		System.out.printf("The formatted String is: %20.3s Thanks\n", "Roger.");
		System.out
				.printf("The formatted String is: %-20.4s Thanks\n", "Roger.");
		/*
		 * %s : will print the string as it is. %20s : will print the string as
		 * it is. If the number has less than 20 digits, the output will be
		 * padded on the left. %.3f : will print maximum 3 characters of the
		 * String.
		 */

		// Formating integers.
		System.out.printf("Total cost is %d;\tTotal quantity is %d\n", 250, 34);

		int integerValue = 4237;
		System.out.printf("Integer %d thats it.\n", integerValue);
		System.out
				.printf("Right justify integer %8d thats it.\n", integerValue);
		System.out.printf("Left justify integer  %-8d thats it.\n",
				integerValue);
		System.out.printf("Zero padding integer  %08d thats it.\n",
				integerValue);
		/*
		 * %d : will print the integer as it is. %8d : will print the integer as
		 * it is. If the number of digits is less than 8, the output will be
		 * padded on the left. %-8d : will print the integer as it is. If the
		 * number of digits is less than 8, the output will be padded on the
		 * right. %08d : will print the integer as it is. If the number of
		 * digits is less than 8, the output will be padded on the left with
		 * zeroes.
		 */

		// Formatting floating point value and double.
		System.out.printf("The total cost is: %f Rs\n", 67.47);
		System.out.printf("The total cost is: %.2f Rs\n", 365.5757);
		System.out.printf("The total cost is: %12.4f Rs\n", 235.476558);
		System.out.printf("The total cost is: %-12.3f Rs\n", 237.47655);
		System.out.printf("The total cost is: %012.5f Rs\n", 567.476558);
		/*
		 * %f : will print the number as it is. %12.4f : will print the number
		 * as it is. If the number has less than 12 digits, the output will be
		 * padded on the left. %.2f : will print maximum 2 decimal digits of the
		 * number. %12.4f : will print maximum 4 decimal digits of the number.
		 * The output will occupy 12 characters at least. If the number of
		 * digits is not enough, it will be padded
		 */

		// To Retrieve a formatted string use the String.format() method.
		String formatted = String.format("Hello %s", "Ruth");
		System.out.println(formatted);

		// Use a double % sign for outputting % sign.
		System.out.printf("Giving it %d%% physically is impossible.", 100);
	}
}