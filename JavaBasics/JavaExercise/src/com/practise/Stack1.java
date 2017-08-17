package com.practise;
/**
 * 
 */

import java.util.Iterator;
import java.util.Stack;

/**
 * @author M1016987
 *
 */
public class Stack1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> stack=new Stack<String>();
		stack.push("Apple");
		stack.push("Ball");
		stack.push("Cat");
		
		Iterator<String> iter=stack.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		

	}

}
