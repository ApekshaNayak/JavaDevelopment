package com.project.collections;

import java.util.ArrayList;
import java.util.Collections;

public class ComparableEx {

	public static void main(String[] args) {
		ArrayList<Student> student = new ArrayList<Student>();
		student.add(new Student(1,"Aarti",28));
		student.add(new Student(8,"Kavita",38));
		student.add(new Student(9,"Janaki",32));
		
		/*Collections.sort(student);*/
		for(Student stu:student){
			System.out.println(stu.rollNo+" "+stu.name+" "+stu.age);
		}
	}

}
