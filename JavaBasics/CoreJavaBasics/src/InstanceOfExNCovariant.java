public class InstanceOfExNCovariant {

	void method(Printable p) {
		if (p instanceof A) {
			A a = (A) p;
			a.method();
		}
		if (p instanceof B) {
			B b = (B) p;
			b.method();
		}
	}

	public static void main(String[] args) {

		InstanceOfExNCovariant inEx = new InstanceOfExNCovariant();
		Printable p = new B();
		inEx.method(p);
		A a = new B().get();
	}

	
}
