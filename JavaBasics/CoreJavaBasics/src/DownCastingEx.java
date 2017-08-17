public class DownCastingEx {
	 void method(Apple a) {
		Banana b = (Banana)a;
		System.out.println("DownCasting done.");
		System.out.println((a instanceof Banana));
		System.out.println(b instanceof Apple);
		System.out.println(b instanceof Banana);
		System.out.println(a instanceof Apple);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DownCastingEx ex = new DownCastingEx();
		Apple a = new Banana();//Upcasting
		//Banana b = (Banana) new Apple();
		System.out.println((a instanceof Banana));
		//System.out.println(b instanceof Apple);
		ex.method(a);
	}

}
