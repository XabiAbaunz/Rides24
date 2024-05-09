package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;
import domain.ReserveStatus;
import domain.Ride;
import domain.Traveler;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BidaiariaErreklamatuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textArrazoia;
	private JLabel lblBidaiariaErreklamatu;
	private JLabel lblBidaiaAukeratu;
	private JLabel lblBidaiariaAukeratu;
	private JButton btnAukeratu;
	private JLabel lblArrazoiaIdatzi;
	private JButton btnErreklamatu;
	private JTextArea textArea;
	private JComboBox comboBidaia;
	private JComboBox comboBidaiari;
	
	private Driver driver;
	private BLFacade facade;
	private Ride bidaia;
	private Traveler bidaiari;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Driver driver = null;
				try {
					BidaiariaErreklamatuGUI frame = new BidaiariaErreklamatuGUI(driver);
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
	public BidaiariaErreklamatuGUI(Driver driver) {
		
		this.driver=driver;
		this.facade = MainGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBidaiariaErreklamatu = new JLabel("Bidaiaria Erreklamatu");
		lblBidaiariaErreklamatu.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiariaErreklamatu.setBounds(10, 10, 416, 13);
		contentPane.add(lblBidaiariaErreklamatu);
		
		lblBidaiaAukeratu = new JLabel("Bidaia aukeratu:");
		lblBidaiaAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiaAukeratu.setBounds(0, 60, 119, 13);
		contentPane.add(lblBidaiaAukeratu);
		
		lblBidaiariaAukeratu = new JLabel("Bidaiaria aukeratu");
		lblBidaiariaAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiariaAukeratu.setBounds(0, 104, 119, 13);
		contentPane.add(lblBidaiariaAukeratu);
		
		btnAukeratu = new JButton("Aukeratu");
		btnAukeratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bidaiari = (Traveler) comboBidaiari.getSelectedItem();
				if(bidaiari != null) {
					textArrazoia.setEnabled(true);
					btnErreklamatu.setEnabled(true);
				} else {
					textArea.setText("Aukeratu erreklamatu nahi duzun bidaiaria.");
				}
			}
		});
		btnAukeratu.setBounds(175, 131, 85, 21);
		contentPane.add(btnAukeratu);
		
		textArrazoia = new JTextField();
		textArrazoia.setBounds(95, 173, 311, 30);
		contentPane.add(textArrazoia);
		textArrazoia.setColumns(10);
		textArrazoia.setEnabled(false);
		
		lblArrazoiaIdatzi = new JLabel("Arrazoia idatzi");
		lblArrazoiaIdatzi.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrazoiaIdatzi.setBounds(0, 181, 96, 13);
		contentPane.add(lblArrazoiaIdatzi);
		
		btnErreklamatu = new JButton("Erreklamatu");
		btnErreklamatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String arrazoia = textArrazoia.getText();
					if(arrazoia.length()>=10) {
						facade.bidaiaErreklamatu(arrazoia, driver.getEmail(), bidaia.getRideNumber());
						textArrazoia.setEnabled(false);
						btnErreklamatu.setEnabled(false);
						comboBidaiari.removeAllItems();
					} else {
						textArea.setText("Idatz ezazu arrazoi luzeagoa");
					}
				} catch(NullPointerException ex) {
					textArea.setText("Idatzi ezazu bidaiaria erreklamatzeko arrazoia.");				}
			}
		});
		btnErreklamatu.setBounds(175, 213, 101, 21);
		contentPane.add(btnErreklamatu);
		btnErreklamatu.setEnabled(false);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(22, 241, 404, 22);
		contentPane.add(textArea);
		
		comboBidaia = new JComboBox();
		comboBidaia.setBounds(116, 56, 290, 21);
		contentPane.add(comboBidaia);
		comboBidaia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBidaiari.removeAllItems();
				bidaia = ((Ride) comboBidaia.getSelectedItem());
				List<ReserveStatus> erreserbak = facade.getAllReservesFromRideNumber(bidaia.getRideNumber());
				for(ReserveStatus rs:erreserbak) {
					if(rs != null && rs.isAccepted()) {
						comboBidaiari.addItem(rs.getTraveler());
					}
				}
				textArrazoia.setEnabled(false);
				btnErreklamatu.setEnabled(false);
			}
		});
		
		comboBidaiari = new JComboBox();
		comboBidaiari.setBounds(116, 100, 290, 21);
		contentPane.add(comboBidaiari);
		
		bidaiakKargatu();
	}
	
	public void bidaiakKargatu() {
		List<Ride> rides = facade.getAllRidesFromEmail(driver.getEmail());
		for(Ride r:rides) {
			if(r != null) {
				comboBidaia.addItem(r);
			}
		}
	}
}
