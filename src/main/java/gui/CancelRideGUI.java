package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Car;
import domain.Driver;
import domain.Ride;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CancelRideGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBidaiaKantzelatu;
	private JLabel lblBidaiak;
	private JComboBox comboKotxeak;
	private DefaultComboBoxModel kotxeComboBoxModel = new DefaultComboBoxModel();
	private JComboBox comboBidaiak;
	private DefaultComboBoxModel bidaiaComboBoxModel = new DefaultComboBoxModel();
	private JButton btnKantzelatu;
	private JLabel lblKotxeak;
	
	private Car car;
	private Ride ride;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Driver d = null;
				try {
					CancelRideGUI frame = new CancelRideGUI(d);
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
	public CancelRideGUI(Driver driver) {
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBidaiaKantzelatu = new JLabel("Bidaia kantzelatu");
		lblBidaiaKantzelatu.setBounds(171, 25, 109, 13);
		contentPane.add(lblBidaiaKantzelatu);
		
		lblBidaiak = new JLabel("Bidaia aukeratu:");
		lblBidaiak.setBounds(10, 141, 85, 13);
		contentPane.add(lblBidaiak);
		
		comboBidaiak = new JComboBox();
		comboBidaiak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnKantzelatu.setEnabled(true);
				ride = (Ride) comboBidaiak.getSelectedItem();
			}
		});
		comboBidaiak.setBounds(119, 137, 269, 21);
		contentPane.add(comboBidaiak);
		
		btnKantzelatu = new JButton("Kantzelatu");
		btnKantzelatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.deleteRideByRideNumber(ride.getRideNumber());
				comboKotxeak.setSelectedItem(null);
				comboBidaiak.setSelectedItem(null);
				btnKantzelatu.setEnabled(false);
			}
		});
		btnKantzelatu.setBounds(171, 212, 85, 21);
		contentPane.add(btnKantzelatu);
		btnKantzelatu.setEnabled(false);
		
		lblKotxeak = new JLabel("Kotxea aukeratu:");
		lblKotxeak.setBounds(10, 83, 99, 13);
		contentPane.add(lblKotxeak);
		
		comboKotxeak = new JComboBox();
		comboKotxeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bidaiaComboBoxModel.removeAllElements();
				btnKantzelatu.setEnabled(false);
				car = (Car) comboKotxeak.getSelectedItem();
				if(car != null) {
					ArrayList<Ride> rides = car.getRides();
					for(Ride r:rides) {
						System.out.println(r.toString());
						bidaiaComboBoxModel.addElement(r);
					}
					comboBidaiak.setModel(bidaiaComboBoxModel);
				}
			}
		});
		comboKotxeak.setBounds(119, 79, 269, 21);
		contentPane.add(comboKotxeak);
		kotxeComboBoxModel.addAll(facade.getCarsByEmail(driver.getEmail()));
		comboKotxeak.setModel(kotxeComboBoxModel);
	}
}
