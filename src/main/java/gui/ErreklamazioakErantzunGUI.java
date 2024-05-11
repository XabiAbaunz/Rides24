package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ErreklamazioakErantzunGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textArrazoia;
	private JLabel lblErreklamazioakErantzun;
	private JLabel lblBidaia;
	private JLabel lblBidaiari;
	private JComboBox comboBidaia;
	private JComboBox comboBidaiari;
	private JButton btnErantzun;
	private JTextArea textBesteArrazoia;
	private JLabel lblBesteArrazoia;
	private JLabel lblArrazoiaIdatzi;
	private JButton btnOnartu;
	private JButton btnEzOnartu;
	
	private Ride bidaia;
	private Traveler bidaiari;
	private Erreklamazio erreklamazioa;
	private JLabel lblErrore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		User u = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErreklamazioakErantzunGUI frame = new ErreklamazioakErantzunGUI(u);
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
	public ErreklamazioakErantzunGUI(User user) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblErreklamazioakErantzun = new JLabel("Erreklamazioak Erantzun");
		lblErreklamazioakErantzun.setHorizontalAlignment(SwingConstants.CENTER);
		lblErreklamazioakErantzun.setBounds(10, 10, 416, 13);
		contentPane.add(lblErreklamazioakErantzun);
		if(user instanceof Driver) {
			lblBidaia = new JLabel("Aukeratu bidaia:");
		} else if(user instanceof Traveler) {
			lblBidaia = new JLabel("Aukeratu erreklamazioa:");
		}
		
		lblBidaia.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaia.setBounds(0, 33, 101, 13);
		contentPane.add(lblBidaia);
		
		lblBidaiari = new JLabel("Aukeratu bidaiaria");
		lblBidaiari.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiari.setBounds(0, 60, 101, 13);
		
		comboBidaia = new JComboBox();
		comboBidaia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Driver) {
					comboBidaiari.removeAllItems();
					lblBesteArrazoia.setEnabled(false);
					textBesteArrazoia.setEnabled(false);
					lblArrazoiaIdatzi.setEnabled(false);
					textArrazoia.setEnabled(false);
					btnOnartu.setEnabled(false);
					btnEzOnartu.setEnabled(false);
					bidaia = (Ride) comboBidaia.getSelectedItem();
					if(bidaia.getErreklamazioak() != null) {
						List<Erreklamazio> erreklamazioak = facade.getAllErreklamazioFromRideNumber(bidaia.getRideNumber());
						for(Erreklamazio err:erreklamazioak) {
							if(err != null) {
								comboBidaiari.addItem(err);
							}
						}
					}
				}
			}
		});
		comboBidaia.setBounds(118, 33, 308, 21);
		contentPane.add(comboBidaia);
		
		comboBidaiari = new JComboBox();
		comboBidaiari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblBesteArrazoia.setEnabled(false);
				textBesteArrazoia.setEnabled(false);
				lblArrazoiaIdatzi.setEnabled(false);
				textArrazoia.setEnabled(false);
				btnOnartu.setEnabled(false);
				btnEzOnartu.setEnabled(false);
			}
		});
		comboBidaiari.setBounds(118, 56, 308, 21);

		/* if(user instanceof Driver) { */
			contentPane.add(lblBidaiari);
			contentPane.add(comboBidaiari);
		/*}*/
		
		btnErantzun = new JButton("Erantzun");
		btnErantzun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Driver) {
					erreklamazioa = (Erreklamazio) comboBidaiari.getSelectedItem();
					if(erreklamazioa != null) {
						lblBesteArrazoia.setEnabled(true);
						textBesteArrazoia.setEnabled(true);
						textBesteArrazoia.setText(erreklamazioa.getBidaiariMezua());
						lblArrazoiaIdatzi.setEnabled(true);
						textArrazoia.setEnabled(true);
						btnOnartu.setEnabled(true);
						btnEzOnartu.setEnabled(true);
						lblErrore.setText("");
					} else {
						lblErrore.setText("Aukeratu erreklamazioa.");
					}
				} else if(user instanceof Traveler) {
					erreklamazioa = (Erreklamazio) comboBidaia.getSelectedItem();
					if(erreklamazioa != null) {
						lblBesteArrazoia.setEnabled(true);
						textBesteArrazoia.setEnabled(true);
						textBesteArrazoia.setText(erreklamazioa.getGidariMezua());
						lblArrazoiaIdatzi.setEnabled(true);
						textArrazoia.setEnabled(true);
						btnOnartu.setEnabled(true);
						btnEzOnartu.setEnabled(true);
						lblErrore.setText("");
					} else {
						lblErrore.setText("Aukeratu erreklamazioa.");
					}
				}
			}
		});
		btnErantzun.setBounds(171, 87, 85, 21);
		contentPane.add(btnErantzun);
		
		textBesteArrazoia = new JTextArea();
		textBesteArrazoia.setEditable(false);
		textBesteArrazoia.setRows(2);
		textBesteArrazoia.setBounds(99, 118, 327, 41);
		contentPane.add(textBesteArrazoia);
		textBesteArrazoia.setEnabled(false);
		
		lblBesteArrazoia = new JLabel("Bestearen arrazoia: ");
		lblBesteArrazoia.setHorizontalAlignment(SwingConstants.CENTER);
		lblBesteArrazoia.setBounds(0, 134, 101, 13);
		contentPane.add(lblBesteArrazoia);
		lblBesteArrazoia.setEnabled(false);
		
		textArrazoia = new JTextField();
		textArrazoia.setBounds(126, 184, 182, 55);
		contentPane.add(textArrazoia);
		textArrazoia.setColumns(10);
		textArrazoia.setEnabled(false);
		
		lblArrazoiaIdatzi = new JLabel("Zure arrazoia idatzi:");
		lblArrazoiaIdatzi.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrazoiaIdatzi.setBounds(0, 205, 116, 13);
		contentPane.add(lblArrazoiaIdatzi);
		lblArrazoiaIdatzi.setEnabled(false);
		
		btnOnartu = new JButton("Onartu");
		btnOnartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.erreklamazioaErantzun(user.getEmail(), bidaia.getRideNumber(), true, "");
				if(user instanceof Driver) {
					List<Ride> bidaiak = facade.getAllRidesFromEmail(user.getEmail());
					for(Ride r:bidaiak) {
						if(r!=null) {
							comboBidaia.addItem(r);
						}
					}
				} else if(user instanceof Traveler) {
					List<Erreklamazio> erreklamazioak = facade.getAllErreklamazioFromEmail(user.getEmail());
					for(Erreklamazio err:erreklamazioak) {
						if(err!=null) {
							comboBidaia.addItem(err.getRide());
						}
					}
				}
				lblBesteArrazoia.setEnabled(false);
				textBesteArrazoia.setEnabled(false);
				lblArrazoiaIdatzi.setEnabled(false);
				textArrazoia.setEnabled(false);
				btnOnartu.setEnabled(false);
				btnEzOnartu.setEnabled(false);
				comboBidaiari.removeAllItems();
			}
		});
		btnOnartu.setBounds(326, 184, 85, 21);
		contentPane.add(btnOnartu);
		btnOnartu.setEnabled(false);
		
		btnEzOnartu = new JButton("Ez onartu");
		btnEzOnartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String arrazoia = textArrazoia.getText();
					if(arrazoia.length() > 10) {
						facade.erreklamazioaErantzun(user.getEmail(), bidaia.getRideNumber(), false, arrazoia);
						if(user instanceof Driver) {
							List<Ride> bidaiak = facade.getAllRidesFromEmail(user.getEmail());
							for(Ride r:bidaiak) {
								if(r!=null) {
									comboBidaia.addItem(r);
								}
							}
						} else if(user instanceof Traveler) {
							List<Erreklamazio> erreklamazioak = facade.getAllErreklamazioFromEmail(user.getEmail());
							for(Erreklamazio err:erreklamazioak) {
								if(err!=null) {
									comboBidaia.addItem(err.getRide());
								}
							}
						}
						lblBesteArrazoia.setEnabled(false);
						textBesteArrazoia.setEnabled(false);
						lblArrazoiaIdatzi.setEnabled(false);
						textArrazoia.setEnabled(false);
						btnOnartu.setEnabled(false);
						btnEzOnartu.setEnabled(false);
						comboBidaiari.removeAllItems();
					} else {
						lblErrore.setText("Idatzi arrazoi luzeagoa.");
					}
				} catch(NullPointerException ex) {
					lblErrore.setText("Idatzi ez onartzeko arrazoia.");
				}
			}
		});
		btnEzOnartu.setBounds(326, 218, 85, 21);
		contentPane.add(btnEzOnartu);
		btnEzOnartu.setEnabled(false);
		
		lblErrore = new JLabel("");
		lblErrore.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrore.setForeground(new Color(255, 0, 0));
		lblErrore.setBounds(274, 91, 152, 13);
		contentPane.add(lblErrore);
		
		if(user instanceof Driver) {
			List<Ride> bidaiak = facade.getAllRidesFromEmail(user.getEmail());
			for(Ride r:bidaiak) {
				if(r!=null) {
					comboBidaia.addItem(r);
				}
			}
		} else if(user instanceof Traveler) {
			List<Erreklamazio> erreklamazioak = facade.getAllErreklamazioFromEmail(user.getEmail());
			for(Erreklamazio err:erreklamazioak) {
				if(err!=null) {
					comboBidaia.addItem(err.getRide());
				}
			}
		}
	}
}
