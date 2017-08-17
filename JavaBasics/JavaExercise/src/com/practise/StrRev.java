package com.practise;
/**
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author M1016987
 *
 */
public class StrRev {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputStreamReader input=new InputStreamReader(System.in);
		BufferedReader reader=new BufferedReader(input);
		//Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the string to be reversed: ");
		String str=reader.readLine();
		int strLen=str.length();
		
		for(int i=strLen-1;i>=0;i--){
			System.out.println(str.charAt(i));
		}
	}

}
