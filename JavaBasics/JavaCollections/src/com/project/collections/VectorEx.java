package com.project.collections;

import java.util.Enumeration;
import java.util.Vector;

public class VectorEx {

	public static void main(String[] args) {
		Vector<String> vectorList = new Vector<String>();
		vectorList.addElement("Rita");
		vectorList.addElement("Meeta");
		vectorList.addElement("Karishma");
		vectorList.addElement("Tanvi");
		vectorList.addElement("Seema");
		vectorList.add("Savita");
		
		Enumeration<String> enu = vectorList.elements();
		while(enu.hasMoreElements()) {
			System.out.println(enu.nextElement());
		}
	}

}
