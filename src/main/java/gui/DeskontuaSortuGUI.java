package gui;

import java.awt.Component;
import businessLogic.BLFacade;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class DeskontuaSortuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_izenburu = new JLabel("Deskontua Sortu");
	private JLabel lbl_zenbat = new JLabel("Zenbateko deskontua");
	private JLabel lbl_data = new JLabel("Iraungitze data aukeratu");
	private JLabel lbl_kodea = new JLabel("Deskontuaren kodea");
	private JButton botoia = new JButton("Deskontua sortu");
	private JTextField textField_kodea;
	private JTextField textField_zenbatekoa;
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private final JLabel lbl_errorea = new JLabel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeskontuaSortuGUI frame = new DeskontuaSortuGUI();
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
	public DeskontuaSortuGUI() {
		BLFacade facade = MainGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl_izenburu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_izenburu.setBounds(159, 10, 129, 40);
		contentPane.add(lbl_izenburu);
		lbl_zenbat.setBounds(250, 46, 112, 18);
		contentPane.add(lbl_zenbat);
		lbl_data.setBounds(21, 109, 118, 18);
		contentPane.add(lbl_data);
		lbl_kodea.setBounds(21, 49, 112, 13);
		contentPane.add(lbl_kodea);
		botoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zenbatekoaText = textField_zenbatekoa.getText();
				int zenbatekoa = Integer.parseInt(zenbatekoaText);
				if(textField_kodea.getText() == null) {
					lbl_errorea.setText("Sartu deskontuaren kodea");
				}else if(zenbatekoa <= 0 ||  zenbatekoa > 100) {
					lbl_errorea.setText("Deskontuaren zenbatekoa ez da zuzena");
				}else if(jCalendar.getDate().before(Calendar.getInstance().getTime())) {
					lbl_errorea.setText("Iraungitze data zuzena aukeratu");
				}else {
					String kodea = textField_kodea.getText();
					int zenbat = zenbatekoa;
					Date iraunData = jCalendar.getDate();
					facade.deskontuaSortu(kodea, zenbat, iraunData);
				}
				
				
			}
		});
		
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//			
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);						
	
					}
					jCalendar.setCalendar(calendarAct);
					int offset = jCalendar.getCalendar().get(Calendar.DAY_OF_WEEK);
					
						if (Locale.getDefault().equals(new Locale("es")))
							offset += 4;
						else
							offset += 5;
				Component o = (Component) jCalendar.getDayChooser().getDayPanel().getComponent(jCalendar.getCalendar().get(Calendar.DAY_OF_MONTH) + offset);
				}}});
		 jCalendar.setBounds(200, 110, 230, 150);
		 contentPane.add(jCalendar);
		
		botoia.setBounds(10, 188, 140, 21);
		contentPane.add(botoia);
		textField_zenbatekoa = new JTextField();
		textField_zenbatekoa.setBounds(250, 74, 119, 20);
		textField_kodea = new JTextField();
		textField_kodea.setBounds(21, 77, 119, 19);
		contentPane.add(textField_kodea);
		textField_kodea.setColumns(10);
		
		contentPane.add(textField_zenbatekoa);
		lbl_errorea.setBounds(10, 219, 158, 44);
		
		contentPane.add(lbl_errorea);
	}
}
