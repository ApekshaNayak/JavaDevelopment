package com.project.dependencyInjection;

import java.util.Date;

public class Answer {
	public int id;
	public Date postedDate;
	public String ans;
	
	public Answer(int id,Date postedDate,String ans){
		this.id=id;
		this.postedDate=postedDate;
		this.ans=ans;
	}
}
