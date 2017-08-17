package com.practise;

public class StaticBlock {
	public StaticBlock() {
		System.out.println("Inside the constructor.");
	}

	static {
		System.out.println("Inside the static block.");
	}

	public static void main(String[] args) {
		System.out.println("In the main class");
		StaticBlock sb = new StaticBlock();
	}

}
