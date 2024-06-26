package gui;

import java.awt.EventQueue;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Car;
import domain.Driver;
import domain.ReserveStatus;
import domain.Ride;
import domain.Traveler;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class AcceptReservesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Driver driver;
	private JButton JButtonBaztertu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreserbaOnartuGUI.Baztertu"));
	private JButton jButtonOnartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreserbaOnartuGUI.Onartu"));
	private JLabel jLabelBidaiariaAukeratu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreserbaOnartuGUI.bidaiariaAukeratu"));
	private JLabel jLabelBidaiaAukeratu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreserbaOnartuGUI.bidaiaAukeratu"));
	private JLabel jLabelErreserbakOnartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreserbaOnartuGUI.ErreserbaOnartu"));
	private JComboBox comboBoxRides = new JComboBox();
	private DefaultComboBoxModel rideComboBoxModel = new DefaultComboBoxModel();
	private JComboBox comboBoxTravelers = new JComboBox();
	private DefaultComboBoxModel travelerComboBoxModel = new DefaultComboBoxModel();
	
	private BLFacade facade;
	
	private Ride ride;
	private ReserveStatus reserve;
	private Traveler traveler;
	private ArrayList<ReserveStatus> erreserbak = new ArrayList<>();


	/**
	 * Create the frame.
	 */
	public AcceptReservesGUI(Driver d) {
		
		this.driver = d;
		
		this.facade = MainGUI.getBusinessLogic();
		
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxRides = new JComboBox();
		comboBoxRides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonOnartu.setEnabled(false);
				JButtonBaztertu.setEnabled(false);
				comboBoxTravelers.removeAllItems();
				erreserbak.clear();
				ride = (Ride) comboBoxRides.getSelectedItem();
				if(ride != null) {
					for(ReserveStatus rs:facade.getAllReservesFromRideNumber(ride.getRideNumber())) {
						if(rs!=null) {
							travelerComboBoxModel.addElement(rs.getTraveler());
							erreserbak.add(rs);
							
						}
					}
				}
				comboBoxTravelers.setModel(travelerComboBoxModel);
			}
		});
		comboBoxRides.setBounds(145, 80, 215, 21);
		
		comboBoxRides.setModel(rideComboBoxModel);
		
		rideComboBoxModel.addAll(facade.getAllRidesFromEmail(driver.getEmail()));
		
		contentPane.add(comboBoxRides);
		contentPane.add(comboBoxTravelers);
		
		
		jLabelErreserbakOnartu.setBounds(190, 36, 143, 13);
		contentPane.add(jLabelErreserbakOnartu);
		

		jLabelBidaiaAukeratu.setBounds(0, 84, 113, 13);
		contentPane.add(jLabelBidaiaAukeratu);
		

		jLabelBidaiariaAukeratu.setBounds(0, 143, 113, 13);
		contentPane.add(jLabelBidaiariaAukeratu);
		
		jButtonOnartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.changeReserveStatus(reserve, true, true);
				facade.addRideByEmail(reserve.getTraveler().getEmail(), ride.getRideNumber());
				comboBoxTravelers.removeAllItems();
			}
		});
		jButtonOnartu.setBounds(106, 197, 85, 21);
		contentPane.add(jButtonOnartu);
		jButtonOnartu.setEnabled(false);
		
		JButtonBaztertu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.changeReserveStatus(reserve, true, false);
				facade.updateMoneyByEmail(reserve.getTraveler().getEmail(), reserve.getFrozenBalance());
				facade.removeReserve(ride.getRideNumber(), reserve.getReserveNumber());
				comboBoxTravelers.removeAllItems();
			}
		});
		JButtonBaztertu.setBounds(249, 197, 85, 21);
		contentPane.add(JButtonBaztertu);
		JButtonBaztertu.setEnabled(false);
		
		comboBoxTravelers = new JComboBox();
		comboBoxTravelers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonOnartu.setEnabled(true);
				JButtonBaztertu.setEnabled(true);
				traveler = (Traveler) comboBoxTravelers.getSelectedItem();
				for(ReserveStatus rs:ride.getReserveList()) {
					if(rs.getTraveler().getEmail().equals(traveler.getEmail())) {
						reserve = rs;
						break;
					}
				}
			}
		});
		comboBoxTravelers.setBounds(148, 139, 212, 21);
		contentPane.add(comboBoxTravelers);
	}
}
