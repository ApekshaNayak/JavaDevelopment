public class B extends A implements Printable {
	void method() {
		System.out.println("in class B");
	}

	B get() {
		System.out.println("in class B covariant return type");
		return this;
		
	}

}
