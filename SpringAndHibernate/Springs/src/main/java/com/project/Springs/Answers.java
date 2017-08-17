package com.project.Springs;

public class Answers {
	public int id;
	public String name;
	public String by;
	
	public Answers(int id, String name,String by) {
		this.id=id;
		this.name=name;
		this.by=by;
	}
	
	public void display() {
		System.out.println(id+" "+name+" "+by);
	}

}
