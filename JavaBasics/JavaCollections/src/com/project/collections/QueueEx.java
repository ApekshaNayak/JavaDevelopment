package com.project.collections;

import java.util.Iterator;
import java.util.PriorityQueue;

public class QueueEx {

	public static void main(String[] args) {
		PriorityQueue<String> queue = new PriorityQueue<String>();
		queue.add("Apple");
		queue.add("Banana");
		queue.add("Chikoo");
		queue.add("Dragon Fruit");
		queue.add("Endive");

		Iterator<String> iter = queue.iterator();
		System.out.println("Elements in the Queue are: ");
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		Iterator<String> iter2 = queue.iterator();
		System.out.println("Elements in the Queue after Poll: ");
		while(iter2.hasNext()) {
			System.out.println(iter2.next());
		}
	}

}
