package com.practise;
public class PassingByValueConstructor {
	public void show(int value) {
		System.out.println("2.Value is: " + value);
		value = value + 1;
		System.out.println("3.Value is: " + value);
	}

	public void show(Person person) {
		System.out.println("2.Person name is : " + person);
		person = new Person("Bob");
		person.setName("Sheila");
		System.out.println("3.Person name is: " + person);
	}

	public static void main(String[] args) {
		PassingByValueConstructor pBV = new PassingByValueConstructor();
		int value = 3;
		System.out.println("1.Value is: " + value);
		pBV.show(value);
		System.out.println("4.Value is: " + value);

		Person person = new Person("Apeksha");
		System.out.println("1.Person name is : " + person);

		pBV.show(person);
		System.out.println("4.Person name is: " + person);
	}

}
