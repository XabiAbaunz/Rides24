package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class DiruaSartuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDirua;
	private JRadioButton rdbtnSartu = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaSartu"));
	private JRadioButton rdbtnAtera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaAtera"));
	private JLabel JLabelDiruaSartuAtera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaSartuAtera"));
	private JLabel JLabelSaldoa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.saldoa"));
	private JLabel jLabelZenbat = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.zenbatDiru"));
	private JButton btnSartuAtera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaSartuAtera"));
	private JTextField textCash;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI();
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
	public DiruaSartuGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnAtera.setBounds(240, 181, 103, 21);
		contentPane.add(rdbtnAtera);
		
		rdbtnSartu.setBounds(87, 181, 103, 21);
		contentPane.add(rdbtnSartu);
		
		textDirua.setBounds(156, 144, 96, 19);
		contentPane.add(textDirua);
		textDirua.setColumns(10);
		
		JLabelSaldoa.setBounds(61, 62, 71, 13);
		contentPane.add(JLabelSaldoa);
		
		jLabelZenbat.setBounds(161, 113, 81, 13);
		contentPane.add(jLabelZenbat);
		
		btnSartuAtera.setBounds(161, 208, 103, 31);
		contentPane.add(btnSartuAtera);
		
		JLabelDiruaSartuAtera.setBounds(178, 21, 64, 13);
		contentPane.add(JLabelDiruaSartuAtera);
		
		textCash = new JTextField();
		textCash.setBounds(161, 147, 86, 20);
		contentPane.add(textCash);
		textCash.setColumns(10);
		
		JTextArea textSaldoa = new JTextArea();
		textSaldoa.setBounds(171, 56, 138, 22);
		contentPane.add(textSaldoa);
	}
}