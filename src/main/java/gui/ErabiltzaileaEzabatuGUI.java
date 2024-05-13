package gui;

import java.awt.Component;
import businessLogic.BLFacade;
import domain.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ErabiltzaileaEzabatuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_izenburu = new JLabel("Sartu erabiltzailearen emaila");
	private JTextField textFieldEmail;
	private final JLabel lbl_errorea = new JLabel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeskontuaSortuGUI frame = new DeskontuaSortuGUI();
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
	public ErabiltzaileaEzabatuGUI() {
		BLFacade facade = MainGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl_izenburu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_izenburu.setBounds(101, 11, 247, 40);
		contentPane.add(lbl_izenburu);
		
		JButton btnEzabatu = new JButton("Ezabatu");
		btnEzabatu.setBounds(159, 103, 129, 23);
		contentPane.add(btnEzabatu);
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String email = textFieldEmail.getText();
					User user = facade.getErabiltzailea(email);
					if(user!=null) {
						facade.erabiltzaileaEzabatu(email);
					}else {
						lbl_errorea.setText("Ez da existitzen erabiltzailea");
					}
				} catch(NullPointerException ex) {
					lbl_errorea.setText("Sartu erabiltzaile baten emaila.");
				}
				
			}});
		
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(159, 61, 129, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		lbl_errorea.setBounds(145, 161, 158, 44);
		
		contentPane.add(lbl_errorea);
	}
}