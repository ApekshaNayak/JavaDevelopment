package com.project.autowiring;

import org.springframework.beans.factory.annotation.Autowired;


public class A {
	
	private B b;

	public A() {
		System.out.println("A is created.");
	}

	public void print() {
		System.out.println("Hello A");
	}

	public void show() {
		print();
		b.print();
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}
