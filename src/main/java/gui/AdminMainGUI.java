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
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AdminMainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton DeskontuaSortuBotoi = new JButton("Deskontua sortu");
	private JLabel lblAukeratu;
	private JButton btnErreklamazioakKudeatu;

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
		DeskontuaSortuBotoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeskontuaSortuGUI frame = new DeskontuaSortuGUI();
				frame.setVisible(true);
			}
		});
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		lblAukeratu = new JLabel("Aukeratu");
		lblAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAukeratu);
		contentPane.add(DeskontuaSortuBotoi);
		
		btnErreklamazioakKudeatu = new JButton("Erreklamazioak kudeatu");
		btnErreklamazioakKudeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErreklamazioakKudeatuGUI frame = new ErreklamazioakKudeatuGUI();
				frame.setVisible(true);
			}
		});
		contentPane.add(btnErreklamazioakKudeatu);
		
		JButton ErabiltzaileaEzabatuBotoi = new JButton("Erabiltzailea ezabatu");
		ErabiltzaileaEzabatuBotoi.setBounds(145, 118, 156, 41);
		contentPane.add(ErabiltzaileaEzabatuBotoi);
		ErabiltzaileaEzabatuBotoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErabiltzaileaEzabatuGUI frame = new ErabiltzaileaEzabatuGUI();
				frame.setVisible(true);
			}
		});
	}

}
