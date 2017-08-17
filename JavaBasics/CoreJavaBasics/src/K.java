
public class K extends J{

	@Override
	public void b() {
		System.out.println("I m b");
		
	}

	@Override
	public void c() {
		System.out.println("I m c");
		
	}

	@Override
	public void d() {
		System.out.println("I m d");
		
	}
	
	public static void main(String[] args){
		I i = new K();
		i.a();
		i.b();
		i.c();
		i.d();
		
	}

}
