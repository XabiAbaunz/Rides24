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

public class BidaiaEgindaGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private BLFacade facade;
    private Traveler traveler;

    private JComboBox<ReserveStatus> comboBoxReservas;
    private JLabel lblEstadoReserva;
	private JButton btnBidaiaBaieztatu;

    /**
     * Create the frame.
     */
    public BidaiaEgindaGUI(Traveler traveler) {
        this.traveler = traveler;
        this.facade = MainGUI.getBusinessLogic();

        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Aukeratu erreserba:");
        lblNewLabel.setBounds(36, 45, 152, 16);
        contentPane.add(lblNewLabel);

        comboBoxReservas = new JComboBox<ReserveStatus>();
        comboBoxReservas.setBounds(200, 42, 200, 22);
        contentPane.add(comboBoxReservas);
        
        lblEstadoReserva = new JLabel("");
        lblEstadoReserva.setBounds(36, 150, 400, 16);
        contentPane.add(lblEstadoReserva);
        
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
        btnBidaiaBaieztatu.setBounds(118, 145, 179, 21);
        btnBidaiaBaieztatu.setEnabled(true);
        
        contentPane.add(btnBidaiaBaieztatu);

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


