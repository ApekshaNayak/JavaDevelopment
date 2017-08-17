package com.practise;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Console {
	public static String readLine() {
		StringBuilder response = new StringBuilder();
		try {
			BufferedInputStream bin = new BufferedInputStream(System.in);
			int in = 0;
			char inChar;
			do {
				in = bin.read();
				inChar = (char) in;
				if (in != -1) {
					response.append(inChar);
				}
			} while ((in != -1) && (inChar != '\n'));
			bin.close();
			return response.toString();
		} catch (IOException e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) {
		System.out
				.println("You are standing at the end of the road before a brick building. "
						+ "Around you is a forest. A small stream flows out of the building and down a gully. ");
		System.out.print("> ");
		String input = Console.readLine();
		System.out.println(input);
		
	}

}
