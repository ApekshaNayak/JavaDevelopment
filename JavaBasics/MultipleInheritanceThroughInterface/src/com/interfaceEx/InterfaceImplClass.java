package com.interfaceEx;

public class InterfaceImplClass implements Printable, Showable {

	@Override
	public void show() {
		System.out.println("This is the show method.");
	}

	@Override
	public void print() {
		System.out.println("this is the print method.");

	}

	public static void main(String[] args) {
		InterfaceImplClass inc = new InterfaceImplClass();
		inc.show();
		inc.print();
	}

}
