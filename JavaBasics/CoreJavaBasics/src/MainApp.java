
public class MainApp {
	public Double add(Double num1, Double num2) {
		return (num1 + num2);
	}

	public int add(int num1, int num2) {
		return (num1 + num2);
	}

	public void inheritanceEx() {
		Apple a1 = new Apple();
		a1.display();
		Apple a2 = new Banana();
		a2.display();
		Banana b1 = new Banana();
		b1.display();
		/*Banana b2 = (Banana) new Apple();
		b2.display();*/

	}

	public static void main(String[] args) {
		MainApp main = new MainApp();
		main.inheritanceEx();
		
		//Overloading Example
		Double val1= main.add(7.3, 3.7);
		int val2 = main.add(6, 4);
		System.out.println("OverLoading Example: "+val1+" "+val2);
	}

}
