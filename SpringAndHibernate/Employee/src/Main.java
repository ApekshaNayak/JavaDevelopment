import javax.swing.JFrame;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.swixml.SwingEngine;

import com.toedter.calendar.JCalendar;

public class Main {
	static String first_name = null;
	static JTextField firstNameField = null;
	Employee employee;
	static JFrame frame;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			new SwingEngine().render("employee_form.xml").setVisible(true);

		} catch (Exception exception) {
			System.out.println("Error: " + exception);
		}
		System.out.println("Output: " + first_name);
	}

}
