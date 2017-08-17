package com.project.Springs;

import java.util.Iterator;
import java.util.List;

public class Questions {
	public int id;
	public String question;
	public List<String> answers;

	public Questions(int id, String question, List<String>answers) {
		this.id = id;
		this.question = question;
		this.answers = answers;
	}

	public void show() {
		System.out.println("Id: " + id);
		System.out.println("Question: " + question);
		Iterator<String> iter = answers.iterator();
		System.out.println("Answers: ");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
