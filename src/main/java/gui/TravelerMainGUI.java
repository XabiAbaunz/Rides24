package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Alerta;
import domain.Traveler;
import domain.User;

import java.awt.GridLayout;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TravelerMainGUI extends JFrame {

	private JPanel contentPane;
	private JLabel JlabelAukeratu  = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.Aukeratu"));
	private JButton rdbtBidaiaBilatu  = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.BidaiaBilatu"));
	
	private Traveler traveler;
	private JButton jButtonDiruaSartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.diruaSartuAtera")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton jButtonErreserbakKudeatu = new JButton("Erreserbak kudeatu");
	
	private JButton jButtonMugimenduakIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.mugimenduakIkusi"));
	private final JButton jButtonErreklamazioakErantzun = new JButton("Erreklamazioak erantzun"); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonAlertakKudeatu = new JButton("Alertak kudeatu"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Traveler t = null;
				try {
					TravelerMainGUI frame = new TravelerMainGUI(t, null);
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
	public TravelerMainGUI(Traveler t, List<Alerta> alertak) {
		this.traveler = t;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{213, 213, 0};
		gbl_contentPane.rowHeights = new int[]{63, 63, 63, 63, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		JlabelAukeratu.setSize(new Dimension(104, 13));
		JlabelAukeratu.setPreferredSize(new Dimension(104, 13));
		JlabelAukeratu.setMinimumSize(new Dimension(104, 13));
		JlabelAukeratu.setMaximumSize(new Dimension(104, 13));
		JlabelAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_JlabelAukeratu = new GridBagConstraints();
		gbc_JlabelAukeratu.gridwidth = 2;
		gbc_JlabelAukeratu.fill = GridBagConstraints.BOTH;
		gbc_JlabelAukeratu.insets = new Insets(0, 0, 5, 5);
		gbc_JlabelAukeratu.gridx = 0;
		gbc_JlabelAukeratu.gridy = 0;
		contentPane.add(JlabelAukeratu, gbc_JlabelAukeratu);
		jButtonMugimenduakIkusi.setBounds(218, 162, 213, 42);
		
		jButtonMugimenduakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI((User)t);
				frame.setVisible(true);
			}
		});
		
		jButtonMugimenduakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI((User)t);
				frame.setVisible(true);
			}
		});
		jButtonErreserbakKudeatu.setBounds(5, 162, 213, 42);
		
		jButtonErreserbakKudeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BidaiakKudeatuGUI frame = new BidaiakKudeatuGUI(t);
				frame.setVisible(true);
			}
		});
		rdbtBidaiaBilatu.setBounds(218, 116, 213, 42);
		rdbtBidaiaBilatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindBookGUI frame = new FindBookGUI(t);
				frame.setVisible(true);
			}
		});
		GridBagConstraints gbc_rdbtBidaiaBilatu = new GridBagConstraints();
		gbc_rdbtBidaiaBilatu.fill = GridBagConstraints.BOTH;
		gbc_rdbtBidaiaBilatu.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtBidaiaBilatu.gridx = 0;
		gbc_rdbtBidaiaBilatu.gridy = 1;
		contentPane.add(rdbtBidaiaBilatu, gbc_rdbtBidaiaBilatu);
		GridBagConstraints gbc_jButtonErreserbakKudeatu = new GridBagConstraints();
		gbc_jButtonErreserbakKudeatu.fill = GridBagConstraints.BOTH;
		gbc_jButtonErreserbakKudeatu.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonErreserbakKudeatu.gridx = 1;
		gbc_jButtonErreserbakKudeatu.gridy = 1;
		contentPane.add(jButtonErreserbakKudeatu, gbc_jButtonErreserbakKudeatu);
		jButtonAlertakKudeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlertakKudeatuGUI frame = new AlertakKudeatuGUI(t);
				frame.setVisible(true);
			}
		});
		jButtonErreklamazioakErantzun.setBounds(5, 211, 213, 42);
		jButtonErreklamazioakErantzun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErreklamazioakErantzunGUI frame = new ErreklamazioakErantzunGUI((User)t);
				frame.setVisible(true);
			}
		});
		
		GridBagConstraints gbc_jButtonErreklamazioakErantzun = new GridBagConstraints();
		gbc_jButtonErreklamazioakErantzun.fill = GridBagConstraints.BOTH;
		gbc_jButtonErreklamazioakErantzun.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonErreklamazioakErantzun.gridx = 0;
		gbc_jButtonErreklamazioakErantzun.gridy = 2;
		contentPane.add(jButtonErreklamazioakErantzun, gbc_jButtonErreklamazioakErantzun);
		jButtonAlertakKudeatu.setBounds(218, 211, 213, 42);
		GridBagConstraints gbc_jButtonAlertakKudeatu = new GridBagConstraints();
		gbc_jButtonAlertakKudeatu.fill = GridBagConstraints.BOTH;
		gbc_jButtonAlertakKudeatu.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonAlertakKudeatu.gridx = 1;
		gbc_jButtonAlertakKudeatu.gridy = 2;
		contentPane.add(jButtonAlertakKudeatu, gbc_jButtonAlertakKudeatu);
		GridBagConstraints gbc_jButtonMugimenduakIkusi = new GridBagConstraints();
		gbc_jButtonMugimenduakIkusi.fill = GridBagConstraints.BOTH;
		gbc_jButtonMugimenduakIkusi.insets = new Insets(0, 0, 0, 5);
		gbc_jButtonMugimenduakIkusi.gridx = 0;
		gbc_jButtonMugimenduakIkusi.gridy = 3;
		contentPane.add(jButtonMugimenduakIkusi, gbc_jButtonMugimenduakIkusi);
		jButtonDiruaSartu.setBounds(5, 116, 213, 42);
		jButtonDiruaSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiruaSartuGUI frame = new DiruaSartuGUI((User)t);
				frame.setVisible(true);
			}
		});
		
		GridBagConstraints gbc_jButtonDiruaSartu = new GridBagConstraints();
		gbc_jButtonDiruaSartu.fill = GridBagConstraints.BOTH;
		gbc_jButtonDiruaSartu.gridx = 1;
		gbc_jButtonDiruaSartu.gridy = 3;
		contentPane.add(jButtonDiruaSartu, gbc_jButtonDiruaSartu);
		if(!alertak.isEmpty()) {
			String mezua = "Alerta berria/k duzu/dituzu: ";
			for(Alerta a:alertak) {
				Date data = a.getDate();
				mezua = mezua + "\n" + a.getFrom() + ", " + a.getTo() + ", " + (data.getYear() + 1900) + "/" + (data.getMonth()+1) + "/" + data.getDate();
			}
			JOptionPane.showMessageDialog(null, mezua);
		}
	}

}
