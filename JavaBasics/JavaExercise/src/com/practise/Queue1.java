package com.practise;
/**
 * 
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author M1016987
 *
 */
public class Queue1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<String> que=new LinkedList<String>();
		que.add("Apple");
		que.add("Ball");
		
		System.out.println(que.remove());
		System.out.println(que.remove());
		//que.remove();
	}

}
