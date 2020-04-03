package da;

import javax.swing.SwingUtilities;

@SuppressWarnings("unused")
public class Window {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GUI());
		
	}
}
