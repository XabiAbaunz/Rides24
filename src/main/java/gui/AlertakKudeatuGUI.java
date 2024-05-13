package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Alerta;
import domain.Traveler;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AlertakKudeatuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	BLFacade facade = MainGUI.getBusinessLogic();
	private JComboBox<String> comboBoxAlertak;
	
	/**
	 * Create the frame.
	 */
	public AlertakKudeatuGUI(Traveler t) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Hauek dira zure alertak");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(121, 23, 176, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_ohar = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(121, 23, 300, 26);
		contentPane.add(lbl_ohar);
		
		comboBoxAlertak = new JComboBox<>();
		comboBoxAlertak.setBounds(29, 60, 389, 30);
		contentPane.add(comboBoxAlertak);
		
	    alertakErakutsi(t);
	
		JButton btnNewButton = new JButton("Alerta ezabatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alerta aukeratutakoa = (Alerta) comboBoxAlertak.getSelectedItem();
				if(aukeratutakoa != null) {
					 facade.alertaEzabatu(aukeratutakoa.getId(), t.getEmail());
					 alertakErakutsi(t);
					 lbl_ohar.setText("alerta behar bezala ezabatu da");
				}else {
					lbl_ohar.setText("aukeratu alerta bat");
				}
			}
		});
		btnNewButton.setBounds(101, 211, 236, 42);
		contentPane.add(btnNewButton);
	}
	public void alertakErakutsi(Traveler t) {
		List<Alerta> alertak = facade.getAlertakByEmail(t.getEmail());
		
		for (Alerta al : alertak) {
			comboBoxAlertak.addItem(al.toString());
		}
		
	}
}
