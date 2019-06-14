package Application;
import java.awt.EventQueue;

import View.MainGUI;

/**
 * Class to run the application.
 * @author Abraham Lee, Jonathan Kim, Patrick Lauer, Joel Johnson
 *
 */
public class Application {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
