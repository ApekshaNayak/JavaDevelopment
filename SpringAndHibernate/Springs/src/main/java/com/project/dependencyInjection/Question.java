package com.project.dependencyInjection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Question {
	public int id;
	public String question;
	public Map<Answer, User> ans;

	public Question(int id, String question, Map<Answer, User> ans) {
		this.id = id;
		this.question = question;
		this.ans = ans;
	}

	public void display() {
		Set<Entry<Answer, User>> ansM = ans.entrySet();
		Iterator<Entry<Answer, User>> iter = ansM.iterator();
		while (iter.hasNext()) {
			Entry<Answer, User> ans = iter.next();
			Answer keyVal = ans.getKey();
			User valval = ans.getValue();
			System.out.println(keyVal.id + "\n " + keyVal.postedDate + " \n"
					+ keyVal.ans);
			System.out.println(valval.id + " \n" + valval.name + " \n"
					+ valval.email);
		}
	}
}
