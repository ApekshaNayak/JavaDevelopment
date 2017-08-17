package com.practise;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Sainath
 *
 */
public class CharSearch {
	/**
	 * @param arg
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		//	Scanner scan=new Scanner("System.in");

		System.out.println("Enter the string: ");
		String str=reader.readLine();

		System.out.println("Enter the character you want to search: ");
		String search=reader.readLine();

		int strlen=str.length();
		int strlen1=search.length();
		int count=0;

		for(int i=0;i<strlen1;i++){
			for(int j=0;j<strlen;j++){
				if(str.charAt(j)==search.charAt(i)){
					count++;
				}
			}
		}
		if(count>=1)System.out.println("character found");
		else System.out.println("character not found");
		System.out.println("Occurance: "+count);


	}
}
