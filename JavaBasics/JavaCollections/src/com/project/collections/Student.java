package com.project.collections;

public class Student /*implements Comparable<Student>*/ {

	int rollNo;
	String name;
	int age;

	Student(int rollNo, String name, int age) {
		this.rollNo = rollNo;
		this.name = name;
		this.age = age;
	}

	/*@Override
	public int compareTo(Student o) {
		if (age > o.age) {
			return 1;
		}
		if (age == o.age) {
			return 0;
		} else
			return -1;
	}*/

}
