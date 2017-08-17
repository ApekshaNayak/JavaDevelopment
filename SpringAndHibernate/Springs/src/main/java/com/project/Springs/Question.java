package com.project.Springs;

import java.util.Iterator;
import java.util.List;

public class Question {
	public int id;
	public String question;
	public List<Answers> answer;

	public Question(int id, String question, List<Answers> answer) {
		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	public void showList() {
		System.out.println("Id: " + id);
		System.out.println("Question: " + question);
		Iterator<Answers> iter = answer.iterator();
		System.out.println("Answers: ");
		while (iter.hasNext()) {
			Answers ans = iter.next();
			System.out.println(ans.id + " " + ans.name + " " + ans.by);
		}
	}
}
