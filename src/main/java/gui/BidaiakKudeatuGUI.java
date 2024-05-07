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

        JLabel lblNewLabel = new JLabel("Aukeratu erreserba:");
        lblNewLabel.setBounds(36, 24, 152, 16);
        contentPane.add(lblNewLabel);

        comboBoxReservas = new JComboBox<ReserveStatus>();
        comboBoxReservas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
           	 ReserveStatus aukeratutakoEgoera = (ReserveStatus) comboBoxReservas.getSelectedItem();
                if (aukeratutakoEgoera != null) {
                    String egoera = aukeratutakoEgoera.getStatus();
                    lblEgoera.setText("Erreserbaren egoera: " + egoera);
                   
                }
        	}});
        comboBoxReservas.setBounds(215, 21, 200, 22);
        contentPane.add(comboBoxReservas);
        
        btnBidaiaBaieztatu = new JButton("Bidaia egin dela baieztatu");
        btnBidaiaBaieztatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 ReserveStatus aukeratutakoa = (ReserveStatus) comboBoxReservas.getSelectedItem();
            	 if( aukeratutakoa != null) {
            		 int erreZenbakia = aukeratutakoa.getReserveNumber();
            		 facade.bidaiaBaieztatu(traveler.getEmail(), erreZenbakia);
            		 erreserbakKargatu();
            	 }
            }
        });
        btnBidaiaBaieztatu.setBounds(24, 232, 179, 21);
        btnBidaiaBaieztatu.setEnabled(true);
        
        contentPane.add(btnBidaiaBaieztatu);
        
        textBalorazio = new JTextField();
        textBalorazio.setBounds(70, 190, 66, 19);
        contentPane.add(textBalorazio);
        textBalorazio.setColumns(10);
        
        textArrazoia = new JTextField();
        textArrazoia.setBounds(225, 143, 190, 79);
        contentPane.add(textArrazoia);
        textArrazoia.setColumns(10);
        
        btnErreklamatu = new JButton("Bidaia erreklamatu");
        btnErreklamatu.setEnabled(true);
        btnErreklamatu.setBounds(236, 232, 179, 21);
        contentPane.add(btnErreklamatu);
        
        lblBalorazio = new JLabel("Balorazioa idatzi:");
        lblBalorazio.setBounds(81, 167, 45, 13);
        contentPane.add(lblBalorazio);
        
        lblArrazoia = new JLabel("Erreklamatzeko arrazoia idatzi:");
        lblArrazoia.setBounds(292, 120, 45, 13);
        contentPane.add(lblArrazoia);
        
        lblEgoera = new JLabel("");
        lblEgoera.setBounds(91, 62, 45, 13);
        contentPane.add(lblEgoera);
        
        rdbtnErreklamatu = new JRadioButton("Bidaia erreklamatu");
        rdbtnErreklamatu.setBounds(259, 93, 103, 21);
        contentPane.add(rdbtnErreklamatu);
        
        rdbtnBaieztatu = new JRadioButton("Bidaia baieztatu");
        rdbtnBaieztatu.setBounds(51, 93, 103, 21);
        contentPane.add(rdbtnBaieztatu);

        erreserbakKargatu();
    }
    private void erreserbakKargatu() {
        List<ReserveStatus> reservas = facade.getAllReservesFromEmail(traveler.getEmail());
        comboBoxReservas.setModel(new DefaultComboBoxModel<ReserveStatus>());
        for (ReserveStatus reserva : reservas) {
        	comboBoxReservas.addItem(reserva) ;
        }
    }
}


