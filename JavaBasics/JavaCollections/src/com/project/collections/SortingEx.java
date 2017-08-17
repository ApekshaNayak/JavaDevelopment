package com.project.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class SortingEx {

	public static void main(String[] args) {
		// String objects.
		ArrayList<String> list = new ArrayList<String>();
		list.add("Abhishek");
		list.add("Cavin");
		list.add("Anish");
		list.add("Aniket");
		list.add("Bob");
		list.add("Zack");
		list.add("Wiley");

		Collections.sort(list);
		ListIterator<String> listIter = list.listIterator();
		while (listIter.hasNext()) {
			System.out.println(listIter.next());
		}
		// Wrapper class objects.
		ArrayList<Integer> intList =new ArrayList<Integer>();
		intList.add(Integer.valueOf(180));
		intList.add(Integer.valueOf(120));
		intList.add(Integer.valueOf(165));
		intList.add(Integer.valueOf(131));
		intList.add(Integer.valueOf(150));
		intList.add(Integer.valueOf(110));
		
		Collections.sort(intList);
		ListIterator<Integer> intIter = intList.listIterator();
		while(intIter.hasNext()) {
			System.out.println(intIter.next());
		}
	}

}
