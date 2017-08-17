package com.practise;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorEx {

	public static void main(String[] args) {
		ArrayList<String> list =  new ArrayList<String>();
		list.add("Blue");
		list.add("Yellow");
		list.add("Green");
		list.add("Pink");
		list.add("Grey");
		list.add("Voilet");
		list.add("Purple");
		list.add("Indigo");
		list.add("White");
		list.add("Orange");
		list.add("Brown");
		list.add("Black");
		System.out.println("List: "+list);
		Iterator itr = list.iterator();
		
		while(itr.hasNext()) {
			Object element = itr.next();
			System.out.print(" \t"+element);
		}
	}

}
