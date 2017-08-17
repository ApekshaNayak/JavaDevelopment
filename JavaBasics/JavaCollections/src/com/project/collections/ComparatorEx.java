package com.project.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class ComparatorEx {

	public static void main(String[] args) {
		ArrayList<Student> student = new ArrayList<Student>();
		student.add(new Student(121, "Avinash", 33));
		student.add(new Student(127, "Anjana", 28));
		student.add(new Student(118, "Zubeida", 31));
		student.add(new Student(125, "Ketki", 26));

		System.out.println("Sorting by name: ");
		Collections.sort(student, new NameComparator());
		ListIterator<Student> stuIter = student.listIterator();
		while (stuIter.hasNext()) {
			Student stu = stuIter.next();
			System.out.println(stu.rollNo + " "
					+ stu.name + " " + stu.age);
		}

		System.out.println("Sorting by age: ");
		Collections.sort(student, new AgeComparator());
		ListIterator<Student> stuIter2 = student.listIterator();
		while (stuIter2.hasNext()) {
			Student stu = stuIter2.next();
			System.out.println(stu.rollNo + " "
					+ stu.name + " " + stu.age);
		}
	}

}
