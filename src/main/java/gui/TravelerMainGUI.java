package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.Traveler;
import domain.User;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TravelerMainGUI extends JFrame {

	private JPanel contentPane;
	private JLabel JlabelAukeratu  = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.Aukeratu"));
	private JButton rdbtBidaiaBilatu  = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.BidaiaBilatu"));
	
	private Traveler traveler;
	private JButton jButtonDiruaSartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.diruaSartuAtera")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton jButtonErreserbakKudeatu = new JButton("Erreserbak kudeatu");
	
	private JButton jButtonMugimenduakIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.mugimenduakIkusi"));
	private final JButton jButtonErreklamazioakErantzun = new JButton("Erreklamazioak erantzun"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Traveler t = null;
				try {
					TravelerMainGUI frame = new TravelerMainGUI(t, false);
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
	public TravelerMainGUI(Traveler t, boolean alerta) {
		this.traveler = t;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 0, 0));
		JlabelAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(JlabelAukeratu);
		if(alerta) JOptionPane.showMessageDialog(null, "Alerta berria/k duzu/dituzu.");
		rdbtBidaiaBilatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindBookGUI frame = new FindBookGUI(t);
				frame.setVisible(true);
			}
		});
		contentPane.add(rdbtBidaiaBilatu);
		jButtonDiruaSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiruaSartuGUI frame = new DiruaSartuGUI((User)t);
				frame.setVisible(true);
			}
		});
		
		jButtonMugimenduakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI((User)t);
				frame.setVisible(true);
			}
		});
		
		contentPane.add(jButtonDiruaSartu);
		
		jButtonErreserbakKudeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BidaiakKudeatuGUI frame = new BidaiakKudeatuGUI(t);
				frame.setVisible(true);
			}
		});
		contentPane.add(jButtonErreserbakKudeatu);
		
		jButtonMugimenduakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI((User)t);
				frame.setVisible(true);
			}
		});
		contentPane.add(jButtonMugimenduakIkusi);
		jButtonErreklamazioakErantzun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErreklamazioakErantzunGUI frame = new ErreklamazioakErantzunGUI((User)t);
				frame.setVisible(true);
			}
		});
		
		contentPane.add(jButtonErreklamazioakErantzun);
	}

}
