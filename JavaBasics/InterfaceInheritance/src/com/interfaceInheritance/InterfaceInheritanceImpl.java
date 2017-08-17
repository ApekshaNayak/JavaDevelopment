package com.interfaceInheritance;

public class InterfaceInheritanceImpl implements Showable{

	@Override
	public void print() {
		System.out.println("This is the print method.");		
	}

	@Override
	public void show() {
		System.out.println("This is the show method.");
	}
	public static void main(int i) {
		System.out.println("This is a main method with int as the argument");
	}
	public static void main(String[] args) {
		InterfaceInheritanceImpl impl =  new InterfaceInheritanceImpl();
		impl.print();
		impl.show();
		main(10);
		main();
	}
	
	public static void main() {
		System.out.println("This is a main method;");
	}
	

}
