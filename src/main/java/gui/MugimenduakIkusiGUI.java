package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Movement;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class MugimenduakIkusiGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelMugimenduak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakIkusiGUI.mugimenduak"));
	private JTextArea textMugimenduak = new JTextArea();
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		User u = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI(u);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MugimenduakIkusiGUI(User u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		jLabelMugimenduak.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelMugimenduak.setBounds(127, 0, 174, 65);
		contentPane.add(jLabelMugimenduak);
		textMugimenduak.setBounds(0, 66, 434, 190);
		contentPane.add(textMugimenduak);
		textMugimenduak.setEditable(false);
		for(Movement m : u.getMovements()){
			textMugimenduak.append(m.toString() + "\n");
		}

	}

}