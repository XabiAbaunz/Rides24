package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Administratzailea;
import domain.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton DeskontuaSortuBotoi = new JButton("Deskontua sortu");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Administratzailea ad = null;
				try {
					AdminMainGUI frame = new AdminMainGUI(ad);
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
	public AdminMainGUI(Administratzailea ad) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		DeskontuaSortuBotoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeskontuaSortuGUI frame = new DeskontuaSortuGUI();
				frame.setVisible(true);
			}
		});
		
		DeskontuaSortuBotoi.setBounds(145, 66, 156, 41);
		contentPane.add(DeskontuaSortuBotoi);
	}

}
