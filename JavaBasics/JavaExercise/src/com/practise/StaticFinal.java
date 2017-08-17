package com.practise;

public class StaticFinal {
	static final String description = "Example of Static and final keywords in JAVA";
	static int count = 0;
	String name;
	int phoneNum;

	public StaticFinal() {
		count++;
	}
	
	public void setInfo(String name, int phoneNum) {
		this.name = name;
		this.phoneNum = phoneNum;
	}
	 public void showInfo() {
		 System.out.println("Name : "+name+ "\t\tPhone number : "+phoneNum);
	 }


	public static void main(String[] args) {
		System.out.println(StaticFinal.description);
		System.out.println("The value of count is : " + StaticFinal.count);
		
		StaticFinal sF1 = new StaticFinal();
		StaticFinal sF2 = new StaticFinal();
		
		sF1.setInfo("Aysha", 2437115);
		sF1.showInfo();
		sF2.setInfo("Aaradhya", 2402570);
		sF2.showInfo();
		
		System.out.println("The Object count is : "+ StaticFinal.count);
		
	
	}

}
