package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErreklamazioakKudeatuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnBidaiaria;
	private JButton btnGidaria;
	private JLabel lblBidaiariarenArrazoia;
	private JLabel lblGidariarenArrazoia;
	private JTextArea textBidaiari;
	private JTextArea textGidari;
	private JButton btnErreklamazioBerria;
	private JLabel lblErreklamazioakKudeatu;
	private JLabel lblArrazoiaNork;
	private JLabel lblBidaia;
	private Erreklamazio erreklamazioa;
	
	private BLFacade facade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErreklamazioakKudeatuGUI frame = new ErreklamazioakKudeatuGUI();
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
	public ErreklamazioakKudeatuGUI() {
		
		this.facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblErreklamazioakKudeatu = new JLabel("Erreklamazioak Kudeatu");
		lblErreklamazioakKudeatu.setHorizontalAlignment(SwingConstants.CENTER);
		lblErreklamazioakKudeatu.setBounds(10, 10, 416, 13);
		contentPane.add(lblErreklamazioakKudeatu);
		
		btnErreklamazioBerria = new JButton("Erreklamazio berria");
		btnErreklamazioBerria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				erreklamazioa = facade.getKonponduGabekoErreklamazioa();
				if(erreklamazioa != null) {
					lblBidaia.setText(erreklamazioa.toString());
					textGidari.setText(erreklamazioa.getGidariMezua());
					textBidaiari.setText(erreklamazioa.getBidaiariMezua());
					btnGidaria.setEnabled(true);
					btnBidaiaria.setEnabled(true);
					lblArrazoiaNork.setEnabled(true);
				}
			}
		});
		btnErreklamazioBerria.setBounds(154, 24, 123, 21);
		contentPane.add(btnErreklamazioBerria);
		
		textGidari = new JTextArea();
		textGidari.setBounds(22, 101, 194, 100);
		contentPane.add(textGidari);
		
		textBidaiari = new JTextArea();
		textBidaiari.setBounds(226, 101, 194, 100);
		contentPane.add(textBidaiari);
		
		lblGidariarenArrazoia = new JLabel("Gidariaren arrazoia");
		lblGidariarenArrazoia.setHorizontalAlignment(SwingConstants.CENTER);
		lblGidariarenArrazoia.setBounds(22, 78, 194, 13);
		contentPane.add(lblGidariarenArrazoia);
		
		lblBidaiariarenArrazoia = new JLabel("Bidaiariaren arrazoia");
		lblBidaiariarenArrazoia.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiariarenArrazoia.setBounds(228, 78, 192, 13);
		contentPane.add(lblBidaiariarenArrazoia);
		
		btnGidaria = new JButton("Gidaria");
		btnGidaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.erreklamazioaKonpondu("g", erreklamazioa.getRide().getRideNumber(), erreklamazioa.getTraveler().getEmail());
				textGidari.setText("");
				textBidaiari.setText("");
				lblBidaia.setText("");
				btnGidaria.setEnabled(false);
				btnBidaiaria.setEnabled(false);
				lblArrazoiaNork.setEnabled(false);
			}
		});
		btnGidaria.setBounds(69, 232, 85, 21);
		contentPane.add(btnGidaria);
		btnGidaria.setEnabled(false);
		
		btnBidaiaria = new JButton("Bidaiaria");
		btnBidaiaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.erreklamazioaKonpondu("b", erreklamazioa.getRide().getRideNumber(), erreklamazioa.getTraveler().getEmail());
				textGidari.setText("");
				textBidaiari.setText("");
				lblBidaia.setText("");
				btnGidaria.setEnabled(false);
				btnBidaiaria.setEnabled(false);
				lblArrazoiaNork.setEnabled(false);
			}
		});
		btnBidaiaria.setBounds(284, 232, 85, 21);
		contentPane.add(btnBidaiaria);
		btnBidaiaria.setEnabled(false);
		
		lblArrazoiaNork = new JLabel("Nork du arrazoia?");
		lblArrazoiaNork.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrazoiaNork.setBounds(10, 210, 416, 13);
		contentPane.add(lblArrazoiaNork);
		lblArrazoiaNork.setEnabled(false);
		
		lblBidaia = new JLabel("");
		lblBidaia.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaia.setBounds(10, 55, 416, 13);
		contentPane.add(lblBidaia);
	}
}
