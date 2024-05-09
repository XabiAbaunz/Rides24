package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import domain.ReserveStatus;
import domain.Traveler;

import businessLogic.BLFacade;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import domain.*;

public class BidaiakKudeatuGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private BLFacade facade;
    private Traveler traveler;

    private JComboBox<ReserveStatus> comboBoxReservas;
	private JButton btnBidaiaBaieztatu;
	private JTextField textBalorazio;
	private JTextField textArrazoia;
	private JRadioButton rdbtnBaieztatu;
	private JRadioButton rdbtnErreklamatu;
	private JLabel lblEgoera;
	private JLabel lblArrazoia;
	private JLabel lblBalorazio;
	private JButton btnErreklamatu;
	private JButton btnAukeratu;
	private JLabel lblAukeratu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Driver gidaria;

    /**
     * Create the frame.
     */
    public BidaiakKudeatuGUI(Traveler traveler) {
        this.traveler = traveler;
        this.facade = MainGUI.getBusinessLogic();

        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblAukeratu = new JLabel("Aukeratu erreserba:");
        lblAukeratu.setBounds(10, 24, 152, 16);
        contentPane.add(lblAukeratu);

        comboBoxReservas = new JComboBox<ReserveStatus>();
        comboBoxReservas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblArrazoia.setEnabled(false);
        		textArrazoia.setEnabled(false);
        		btnErreklamatu.setEnabled(false);
        		lblBalorazio.setEnabled(false);
        		textBalorazio.setEnabled(false);
        		btnBidaiaBaieztatu.setEnabled(false);
                rdbtnBaieztatu.setEnabled(false);
                rdbtnErreklamatu.setEnabled(false);
        	}
        });
        comboBoxReservas.setBounds(121, 21, 200, 22);
        contentPane.add(comboBoxReservas);
        
        btnBidaiaBaieztatu = new JButton("Bidaia egin dela baieztatu");
        btnBidaiaBaieztatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			double balorazioa = Double.parseDouble(textBalorazio.getText());
        			if(balorazioa>=0.0 || balorazioa<=10.0) {
        				facade.addBalorazioByEmail(gidaria.getEmail(), balorazioa);
        				facade.bidaiaBaieztatu(traveler.getEmail(), ((ReserveStatus) comboBoxReservas.getSelectedItem()).getReserveNumber());
                		lblArrazoia.setEnabled(false);
                		textArrazoia.setEnabled(false);
                		btnErreklamatu.setEnabled(false);
                		lblBalorazio.setEnabled(false);
                		textBalorazio.setEnabled(false);
                		btnBidaiaBaieztatu.setEnabled(false);
                		buttonGroup.clearSelection();
                		comboBoxReservas.removeAllItems();
                		erreserbakKargatu();
        			} else {
        				lblEgoera.setText("0-10 bitarteko zenbaki bat sartu behar duzu.");
        			}
        		} catch(NullPointerException  e1) {
        			lblEgoera.setText("Sartu balorazio bat gidariarentzat bat.");
        		} catch(NumberFormatException e1) {
        			lblEgoera.setText("Sartu duzun balorazioa ez da zenbaki bat.");
        		}
        		
        	}
        });
        btnBidaiaBaieztatu.setBounds(24, 232, 179, 21);
        btnBidaiaBaieztatu.setEnabled(true);
        btnBidaiaBaieztatu.setEnabled(false);
        
        contentPane.add(btnBidaiaBaieztatu);
        
        textBalorazio = new JTextField();
        textBalorazio.setBounds(70, 190, 66, 19);
        contentPane.add(textBalorazio);
        textBalorazio.setColumns(10);
        textBalorazio.setEnabled(false);
        
        textArrazoia = new JTextField();
        textArrazoia.setBounds(225, 143, 190, 79);
        contentPane.add(textArrazoia);
        textArrazoia.setColumns(10);
        textArrazoia.setEnabled(false);
        
        btnErreklamatu = new JButton("Bidaia erreklamatu");
        btnErreklamatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String arrazoia = textArrazoia.getText();
        			if(arrazoia.length()>10) {
        				facade.bidaiaErreklamatu(arrazoia, traveler.getEmail(), ((ReserveStatus) comboBoxReservas.getSelectedItem()).getReserveNumber());
                		lblArrazoia.setEnabled(false);
                		textArrazoia.setEnabled(false);
                		btnErreklamatu.setEnabled(false);
                		lblBalorazio.setEnabled(false);
                		textBalorazio.setEnabled(false);
                		btnBidaiaBaieztatu.setEnabled(false);
                		buttonGroup.clearSelection();
                		comboBoxReservas.removeAllItems();
                		erreserbakKargatu();
        			} else {
        				lblEgoera.setText("Sartu duzun arrazoia motzegia da.");
        			}
        		} catch(NullPointerException e1) {
        			lblEgoera.setText("Sartu bidaia erreklamatzearen arrrazoia.");
        		}
        	}
        });
        btnErreklamatu.setEnabled(true);
        btnErreklamatu.setBounds(236, 232, 179, 21);
        contentPane.add(btnErreklamatu);
        btnErreklamatu.setEnabled(false);
        
        lblBalorazio = new JLabel("Balorazioa idatzi (1-10):");
        lblBalorazio.setBounds(51, 167, 103, 13);
        contentPane.add(lblBalorazio);
        lblBalorazio.setEnabled(false);
        
        lblArrazoia = new JLabel("Erreklamatzeko arrazoia idatzi:");
        lblArrazoia.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrazoia.setBounds(236, 120, 179, 13);
        contentPane.add(lblArrazoia);
        lblArrazoia.setEnabled(false);
        
        lblEgoera = new JLabel("");
        lblEgoera.setBounds(91, 62, 262, 13);
        contentPane.add(lblEgoera);
        
        rdbtnErreklamatu = new JRadioButton("Bidaia erreklamatu");
        rdbtnErreklamatu.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnErreklamatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblArrazoia.setEnabled(true);
        		textArrazoia.setEnabled(true);
        		btnErreklamatu.setEnabled(true);
        		lblBalorazio.setEnabled(false);
        		textBalorazio.setEnabled(false);
        		btnBidaiaBaieztatu.setEnabled(false);
        	}
        });
        buttonGroup.add(rdbtnErreklamatu);
        rdbtnErreklamatu.setBounds(225, 93, 190, 21);
        contentPane.add(rdbtnErreklamatu);
        rdbtnErreklamatu.setEnabled(false);
        
        rdbtnBaieztatu = new JRadioButton("Bidaia baieztatu");
        rdbtnBaieztatu.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnBaieztatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblBalorazio.setEnabled(true);
        		textBalorazio.setEnabled(true);
        		btnBidaiaBaieztatu.setEnabled(true);
        		lblArrazoia.setEnabled(false);
        		textArrazoia.setEnabled(false);
        		btnErreklamatu.setEnabled(false);
        	}
        });
        buttonGroup.add(rdbtnBaieztatu);
        rdbtnBaieztatu.setBounds(33, 93, 170, 21);
        contentPane.add(rdbtnBaieztatu);
        rdbtnBaieztatu.setEnabled(false);
        
        btnAukeratu = new JButton("Aukeratu");
        btnAukeratu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReserveStatus aukeratutakoEgoera = (ReserveStatus) comboBoxReservas.getSelectedItem();
                if (aukeratutakoEgoera != null) {
                    String egoera = aukeratutakoEgoera.getStatus();
                    lblEgoera.setText("Erreserbaren egoera: " + egoera);
                    if(egoera.equals("Onartua")) {
                        rdbtnErreklamatu.setEnabled(true);
                        rdbtnBaieztatu.setEnabled(true);
                        gidaria = aukeratutakoEgoera.getRide().getCar().getDriver();
                    }
                }
        	}
        });
        btnAukeratu.setBounds(331, 22, 95, 21);
        contentPane.add(btnAukeratu);
        
        buttonGroup.add(rdbtnErreklamatu);
        buttonGroup.add(rdbtnBaieztatu);
        buttonGroup.clearSelection();

        erreserbakKargatu();
    }
    private void erreserbakKargatu() {
    	boolean b = false;
        List<ReserveStatus> reservas = facade.getAllReservesFromEmail(traveler.getEmail());
        comboBoxReservas.setModel(new DefaultComboBoxModel<ReserveStatus>());
        for (ReserveStatus reserva : reservas) {
        	for(Erreklamazio e:reserva.getRide().getErreklamazioak()) {
        		if(e.getTraveler().equals(traveler)) {
        			b = true;
        		}
        	}
        	if(!b) comboBoxReservas.addItem(reserva);
        }
    }
}


