package views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenericStuff extends JFrame {
	private static final long serialVersionUID = 1L;

	public GenericStuff() {

	}

	public void call_frame(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}

	public void hover(JLabel label, JLabel innerlabel, JPanel jPanel) {
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseExited(MouseEvent e) {
				innerlabel.setForeground(Color.BLACK);
				jPanel.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				innerlabel.setForeground(new Color(230, 230, 250));
				jPanel.setBackground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
	}
}
