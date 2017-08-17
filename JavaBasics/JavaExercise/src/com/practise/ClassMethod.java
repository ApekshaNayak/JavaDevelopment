package com.practise;
//Class: Is a template for creating objects or a blueprint for creating objects.
// Class contains:
//1. Data/Attributes(instance variable)
//2. Subroutines/Behaviours(methods)
// A file can have only one public class but a number of other classes.

//Object: The principal building block of object-oriented programs.
//Each object is a programming unit consisting of data and functionality.

//Method: A java method is a collection of statements grouped together to perform an operation.

class Person {
	String name;
	int age;
	
	public Person(String name) {
		this.name = name;
	}
	public Person() {
		
	}
	void eat() {
		System.out.println("I am eating an Apple");
	}
	
	public void setName(String name) {
		this.name =name ;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}

public class ClassMethod {
public static void main(String[] args) {
	Person person1 = new Person();
	person1.name = "Joe";
	person1.age = 25;
	
	System.out.println("Name: "+person1.name +"\nAge: "+person1.age);
	
	Person person2 = new Person();
	person2.name = "Susan";
	person2.age = 35;
	
	System.out.println("Name: "+person2.name +"\nAge: "+person2.age);
	person2.eat();
}
}


