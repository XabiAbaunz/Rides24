package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AddCarGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMarka;
	private JTextField textModeloa;
	private JTextField textEserlekuKop;
	private JLabel lblKotxeaGehitu=  new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.kotxeaGehitu"));
	private JLabel lblMarka=  new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.marka"));
	private JLabel lblModeloa=  new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.modeloa"));
	private JLabel lblEserlekuKop=  new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.eserlekuKop"));
	private JButton btnAddCar =  new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.kotxeaGehitu"));
	private String marka;
	private String modeloa;
	private int eserlekuKop;
	private JTextArea erroreArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Driver d = null;
				try {
					AddCarGUI frame = new AddCarGUI(d);
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
	public AddCarGUI(Driver driver) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblKotxeaGehitu.setBounds(188, 24, 86, 13);
		contentPane.add(lblKotxeaGehitu);
		
		
		lblMarka.setBounds(23, 70, 122, 13);
		contentPane.add(lblMarka);
		
		
		lblModeloa.setBounds(23, 110, 122, 13);
		contentPane.add(lblModeloa);
		
		
		lblEserlekuKop.setBounds(23, 155, 133, 13);
		contentPane.add(lblEserlekuKop);
		
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				marka = textMarka.getText();
				modeloa = textModeloa.getText();
				try {
					eserlekuKop = Integer.parseInt(textEserlekuKop.getText());
				} catch (NumberFormatException ex) {
					erroreArea.setText("Datuak ez daude ongi osatuta.");
				}
				if((marka != "" && modeloa != "" && eserlekuKop > 0)) {
					facade.addCarByEmail(driver.getEmail(), marka, modeloa, eserlekuKop);
					erroreArea.setText("Kotxea gehitu da.");
				} else {
					erroreArea.setText("Datuak ez daude osatuta.");
				}
			}
		});
		btnAddCar.setBounds(163, 191, 111, 30);
		contentPane.add(btnAddCar);
		
		textMarka = new JTextField();
		textMarka.setBounds(178, 67, 212, 19);
		contentPane.add(textMarka);
		textMarka.setColumns(10);
		
		textModeloa = new JTextField();
		textModeloa.setBounds(177, 107, 213, 19);
		contentPane.add(textModeloa);
		textModeloa.setColumns(10);
		
		textEserlekuKop = new JTextField();
		textEserlekuKop.setBounds(217, 152, 57, 19);
		contentPane.add(textEserlekuKop);
		textEserlekuKop.setColumns(10);
		
		erroreArea = new JTextArea();
		erroreArea.setBounds(110, 231, 212, 22);
		contentPane.add(erroreArea);
	}
}
