import java.io.IOException;
/*Throws is used to declare a exception usually a checked Exception*/
public class ThrowsExample {
	void mymethod(int num) throws IOException, ClassNotFoundException {
		if (num == 1)
			throw new IOException("Exception Message1");
		else
			throw new ClassNotFoundException("Exception Message2");
	}

	public static void main(String args[]) {
		try {
			ThrowsExample obj = new ThrowsExample();
			obj.mymethod(1);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
