package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;
import domain.User;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DriverMainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DriverMainGUI.SelectOption"));
	private JButton jButtonCreateRide = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DriverMainGUI.CreateRide"));
	private JButton jButtonAcceptReservation = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DriverMainGUI.AcceptReservation"));

	private Driver driver;
	private final JButton jButtonDiruaSartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.diruaSartuAtera")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonBidaiaKantzelatu = new JButton("Bidaia kantzelatu");
	private final JButton jButtonKotxeaGehitu = new JButton("Kotxea gehitu");
	
	private JButton jButtonMugimenduakIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.mugimenduakIkusi"));
	private final JButton jButtonBidaiariaErreklamatu = new JButton("Bidaiaria erreklamatu");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Driver d = null;
				try {
					DriverMainGUI frame = new DriverMainGUI(d);
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
	public DriverMainGUI(Driver d) {
		this.driver = d;
		
		BLFacade facade = MainGUI.getBusinessLogic();

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 0, 0));
		
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(jLabelSelectOption);
		jButtonCreateRide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateRideGUI frame = new CreateRideGUI(driver);
				frame.setVisible(true);
			}
		});
		
		jButtonMugimenduakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI((User)d);
				frame.setVisible(true);
			}
		});

		contentPane.add(jButtonMugimenduakIkusi);
		
		contentPane.add(jButtonCreateRide);
		jButtonAcceptReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcceptReservesGUI frame = new AcceptReservesGUI(driver);
				frame.setVisible(true);
			}
		});
		
		contentPane.add(jButtonAcceptReservation);
		jButtonDiruaSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiruaSartuGUI frame = new DiruaSartuGUI((User)driver);
				frame.setVisible(true);
			}
		});
		
		contentPane.add(jButtonDiruaSartu);
		jButtonKotxeaGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCarGUI frame = new AddCarGUI(driver);
				frame.setVisible(true);
			}
		});
		
		contentPane.add(jButtonKotxeaGehitu);
		jButtonBidaiaKantzelatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelRideGUI frame = new CancelRideGUI(driver);
				frame.setVisible(true);
			}
		});
		
		contentPane.add(jButtonBidaiaKantzelatu);
		jButtonBidaiariaErreklamatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BidaiariaErreklamatuGUI frame = new BidaiariaErreklamatuGUI(driver);
				frame.setVisible(true);
			}
		});
		
		contentPane.add(jButtonBidaiariaErreklamatu);
	}

}
