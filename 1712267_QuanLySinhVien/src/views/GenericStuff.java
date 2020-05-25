package views;

import javax.swing.JFrame;

public class GenericStuff extends JFrame {
	private static final long serialVersionUID = 1L;

	public GenericStuff() {

	}

	public void call_frame(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
}
