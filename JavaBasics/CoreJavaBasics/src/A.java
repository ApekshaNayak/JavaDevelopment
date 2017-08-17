public class A implements Printable {
	void method() {
		System.out.println("in class A");
	}

	A get() {
		System.out.println("in class A covariant");
		return this;
	}

}
