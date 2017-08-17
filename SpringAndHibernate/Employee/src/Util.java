import java.awt.Component;
import java.awt.Frame;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

public class Util {
	String layout;
	String first_name = null;

	public JTextField getJTextField() {
		Frame frame = SwingEngine.getAppFrame();
		{
			Component paneList = frame.getComponent(0);
			final JRootPane rootPane = (JRootPane) paneList;
			final JLayeredPane layeredPane = (JLayeredPane) rootPane
					.getComponent(1);
			final JPanel layeredPanel = (JPanel) layeredPane.getComponent(0);
			final JPanel mainPanel = (JPanel) layeredPanel.getComponent(0);
			final JTextField firstNameFields = (JTextField) mainPanel
					.getComponent(1);
						
			return firstNameFields;
		}
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
}
