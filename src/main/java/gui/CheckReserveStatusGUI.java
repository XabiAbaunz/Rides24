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

public class CheckReserveStatusGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private BLFacade facade;
    private Traveler traveler;

    private JComboBox<ReserveStatus> comboBoxReservas;
    private JLabel lblEstadoReserva;

    /**
     * Create the frame.
     */
    public CheckReserveStatusGUI(Traveler traveler) {
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

        JButton btnConsultarEstado = new JButton("Egoera kontsultatu");
        btnConsultarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 ReserveStatus reservaSeleccionada = (ReserveStatus) comboBoxReservas.getSelectedItem();
                 if (reservaSeleccionada != null) {
                     String estado = reservaSeleccionada.getStatus();
                     

                     lblEstadoReserva.setText("Erreserbaren egoera: " + estado);
                 } else {
                  
                     lblEstadoReserva.setText("Aukeratu erreserba bat");
                 }
             }
        });
        btnConsultarEstado.setBounds(137, 106, 150, 25);
        contentPane.add(btnConsultarEstado);
        
        lblEstadoReserva = new JLabel("");
        lblEstadoReserva.setBounds(36, 150, 400, 16);
        contentPane.add(lblEstadoReserva);

        erreserbakKargatu();
    }

    private void erreserbakKargatu() {
        List<ReserveStatus> reservas = facade.getAllReservesFromEmail(traveler.getEmail());
        comboBoxReservas.setModel(new DefaultComboBoxModel<ReserveStatus>());
        for (ReserveStatus reserva : reservas) {
            comboBoxReservas.addItem(reserva);
        }
    }
}


