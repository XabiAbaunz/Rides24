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
import java.util.ResourceBundle;

import domain.ReserveStatus;
import domain.Traveler;

import businessLogic.BLFacade;
import javax.swing.JTextField;

public class BidaiaEgindaBaloratuGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private BLFacade facade;
    private Traveler traveler;

    private JComboBox<ReserveStatus> comboBoxReservas;
    private JLabel lblErreserbaAukeratu=new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BidaiaEgindaBaloratuGUI.erreserbaAukeratu"));
	private JButton btnBidaiaBaieztatu=new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaEgindaBaloratuGUI.bidaiaBaieztatu"));
	private JLabel lblBidaiaBaloratu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BidaiaEgindaBaloratuGUI.bidaiaBaloratu"));
	private JTextField textBalorazioa = new JTextField();
    /**
     * Create the frame.
     */
    public BidaiaEgindaBaloratuGUI(Traveler traveler) {
        this.traveler = traveler;
        this.facade = MainGUI.getBusinessLogic();

        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        lblErreserbaAukeratu.setBounds(36, 45, 152, 16);
        contentPane.add(lblErreserbaAukeratu);

        comboBoxReservas = new JComboBox<ReserveStatus>();
        comboBoxReservas.setBounds(200, 42, 200, 22);
        contentPane.add(comboBoxReservas);
        
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
        btnBidaiaBaieztatu.setBounds(120, 161, 179, 21);
        btnBidaiaBaieztatu.setEnabled(true);
        
        contentPane.add(btnBidaiaBaieztatu);
        
        lblBidaiaBaloratu.setBounds(46, 107, 93, 22);
        contentPane.add(lblBidaiaBaloratu);
        
        textBalorazioa.setBounds(172, 110, 140, 16);
        contentPane.add(textBalorazioa);
        textBalorazioa.setColumns(10);

        erreserbakKargatu();
    }
    private void erreserbakKargatu() {
        List<ReserveStatus> reservas = facade.getAllReservesFromEmail(traveler.getEmail());
        comboBoxReservas.setModel(new DefaultComboBoxModel<ReserveStatus>());
        for (ReserveStatus reserva : reservas) {
        	//Onartutakoak bakarrik gehitu
        	if(reserva.isAccepted()) {
        		comboBoxReservas.addItem(reserva) ;
        	}
        }
    }
}


