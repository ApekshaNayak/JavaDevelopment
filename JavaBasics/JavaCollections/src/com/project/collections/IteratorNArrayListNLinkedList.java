package com.project.collections;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class IteratorNArrayListNLinkedList {

	public static void main(String[] args) {
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add("Bangalore");
		arrList.add("Mumbai");
		arrList.add("Delhi");
		arrList.add("Chennai");
		arrList.add("Hyderabad");
		arrList.add("Delhi");
		Iterator<String> iterArrList = arrList.iterator();
		while (iterArrList.hasNext()) {
			System.out.println("Array Element at " +iterArrList.next());
		}
		
		LinkedList<String> linkList = new LinkedList<String>();
		linkList.add("Blue");
		linkList.add("Yellow");
		linkList.add("Green");
		linkList.add("Red");
		linkList.add("Cyan");
		linkList.add("Red");
		linkList.addFirst("Hey Colors");
		linkList.addLast("Bye Colors");
				
		System.out.println("Elements in forward direction: ");
		ListIterator<String> listIter = arrList.listIterator();
		while (listIter.hasNext()) {
			System.out.println("LinkArray Element at " +listIter.next());
		}
		
		System.out.println("Elements in backward direction: ");
		while (listIter.hasPrevious()) {
			System.out.println("LinkArray Element at " +listIter.previous());
		}

		System.out.println("Get method sample: "+ listIter.getClass().getName());
	}

}
