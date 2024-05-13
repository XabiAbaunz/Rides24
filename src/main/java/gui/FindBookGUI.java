package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.ReserveStatus;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class FindBookGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Rides")); 
	private JButton jButtonBook = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindBookGUI.Book"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();

	private List<Date> datesWithRidesCurrentMonth = new Vector<Date>();

	private JTable tableRides= new JTable();

	private DefaultTableModel tableModelRides;


	private String[] columnNamesRides = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Driver"), 
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NPlaces"), 
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Price"),
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Balorazioa")
	};
	
	private Traveler traveler;
	
	private Ride ride;
	private final JLabel lbl_deskontua = new JLabel("Sartu deskontu kodea");
	private final JTextField text_deskontua = new JTextField();


	private int berezkoDeskontua = 0;


	private JButton botoi_deskontua = new JButton("deskontua aplikatu");
	private final JButton jButtonAlerta = new JButton("Alerta sortu"); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textFrom;
	private JTextField textTo;
	private final JButton btnFindRides = new JButton("Bilatu bidaia"); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblOrigin = new JLabel("Jatorria: ");
	private final JLabel lblDestination = new JLabel("Helburua: ");
	
	DateFormat dateformat1;
	
	String from;
	String to;


	public FindBookGUI(Traveler t)
	{
		BLFacade facade = MainGUI.getBusinessLogic();
		
		botoi_deskontua.setEnabled(true);
		text_deskontua.setBounds(175, 335, 122, 25);
		this.traveler=t;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.FindRides"));

		jLabelEventDate.setBounds(new Rectangle(457, 6, 140, 25));
		jLabelEvents.setBounds(38, 206, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(531, 420, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(300, 50, 225, 150));


		// Code for JCalendar
		jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{
				try {
					jButtonBook.setEnabled(false);
					jButtonAlerta.setEnabled(false);
	
					if (propertychangeevent.getPropertyName().equals("locale"))
					{
						jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
					}
					else if (propertychangeevent.getPropertyName().equals("calendar"))
					{
						calendarAnt = (Calendar) propertychangeevent.getOldValue();
						calendarAct = (Calendar) propertychangeevent.getNewValue();
						
	
						
						dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
	
						int monthAnt = calendarAnt.get(Calendar.MONTH);
						int monthAct = calendarAct.get(Calendar.MONTH);
	
						if (monthAct!=monthAnt) {
							if (monthAct==monthAnt+2) {
								// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
								// Con este código se dejará como 1 de febrero en el JCalendar
								calendarAct.set(Calendar.MONTH, monthAnt+1);
								calendarAct.set(Calendar.DAY_OF_MONTH, 1);
							}						
	
							jCalendar1.setCalendar(calendarAct);
	
						}
						
							tableModelRides.setRowCount(0);
							tableRides.clearSelection();
							tableModelRides.setDataVector(null, columnNamesRides);
							tableModelRides.setColumnCount(5); // another column added to allocate ride objects
							jButtonBook.setEnabled(false);
							jButtonAlerta.setEnabled(false);
	
					}
				} catch(NullPointerException ex) {
					jLabelEvents.setText("Idatzi bidaiaren jatorria eta helburua.");
				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(38, 232, 603, 93));

		scrollPaneEvents.setViewportView(tableRides);
		tableModelRides = new DefaultTableModel(null, columnNamesRides);

		tableRides.setModel(tableModelRides);

		tableModelRides.setDataVector(null, columnNamesRides);
		tableModelRides.setColumnCount(5); // another column added to allocate ride objects

		tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
		
		tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3)); // not shown in JTable
		tableRides.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					int selectedRow = tableRides.getSelectedRow();
                    int selectedColumn = 4;
                    try {
                    	ride = (Ride) tableModelRides.getValueAt(selectedRow, selectedColumn);
                    } catch(ArrayIndexOutOfBoundsException ex) {
                    }
                    jButtonBook.setEnabled(true);
                    jButtonAlerta.setEnabled(false);
				}
			}
			
		});

		this.getContentPane().add(scrollPaneEvents, null);
		paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);
		
		jButtonBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveStatus rs = new ReserveStatus(ride.getPrice(), traveler, ride);
				facade.updateMoneyByEmail(traveler.getEmail(), -1 *(ride.getPrice() * (1-berezkoDeskontua)));
				boolean b = facade.addReserve(rs, ride.getRideNumber());
				if(!b) {
					jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindBookGUI.ErreserbakBeteta"));
				}
			}
		});
		jButtonBook.setBounds(300, 420, 130, 30);
		getContentPane().add(jButtonBook);
		jButtonBook.setEnabled(false);
		lbl_deskontua.setBounds(38, 335, 122, 25);
		
		getContentPane().add(lbl_deskontua);
		
		getContentPane().add(text_deskontua);
		
		JTextArea oharArea = new JTextArea();
		oharArea.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		oharArea.setBounds(95, 423, 122, 30);
		getContentPane().add(oharArea);
		
		
		
		botoi_deskontua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(text_deskontua.getText() == "") {
					oharArea.setText("Sartu deskontu kodea");
				}else{
					int desk = facade.deskontuaEgiaztatu(text_deskontua.getText(), t.getEmail());
					if(desk == -1) { 
						oharArea.setText("Deskontua ez da exisitzen");
					}else if(desk == -2){ 
						oharArea.setText("Deskontua iada iraungita");
					}else if(desk == -3) {
						oharArea.setText("Deskontua iada erabili duzu");
					}else {
						oharArea.setText("Deskontua behar bezala aplikatu da  %" +  desk);
						berezkoDeskontua = desk;
						botoi_deskontua.setEnabled(false);
					}
				}
			}
		});
		botoi_deskontua.setBounds(75, 370, 172, 37);
		getContentPane().add(botoi_deskontua);
		jButtonAlerta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.addAlertaByEmail(traveler.getEmail(), from, to, jCalendar1.getDate());
				jLabelEvents.setText("Alerta sortu da.");
			}
		});
		jButtonAlerta.setBounds(300, 374, 130, 29);
		jButtonAlerta.setEnabled(false);
		
		getContentPane().add(jButtonAlerta);
		
		textFrom = new JTextField();
		textFrom.setText("");
		textFrom.setBounds(95, 57, 173, 19);
		getContentPane().add(textFrom);
		textFrom.setColumns(10);
		
		textTo = new JTextField();
		textTo.setText("");
		textTo.setBounds(95, 86, 172, 19);
		getContentPane().add(textTo);
		textTo.setColumns(10);
		btnFindRides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jButtonAlerta.setEnabled(false);
					from = textFrom.getText();
					to = textTo.getText();
					if(!from.equals("") && !to.equals("")) {
						List<domain.Ride> rides=facade.getRides(from,to,UtilDate.trim(jCalendar1.getDate()));
		
						if (rides.isEmpty() ) {
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NoRides")+ ": "+dateformat1.format(calendarAct.getTime()));
							jButtonAlerta.setEnabled(true);
							jButtonBook.setEnabled(false);
							tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(4));
						} else {
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Rides")+ ": "+dateformat1.format(calendarAct.getTime()));
						}
						for (domain.Ride ride:rides){
							Vector<Object> row = new Vector<Object>();
							row.add(ride.getCar().getDriver().getName());
							row.add(ride.getnPlaces());
							row.add(ride.getPrice());
							double balorazioa = facade.getBalorazioa(ride.getCar().getDriver().getEmail());
							if (balorazioa != -1) {
								row.add(balorazioa);
							}else {
								row.add("gidariak ez du baloraziorik");
							}
							row.add(ride); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,3)
							tableModelRides.addRow(row);		
						}
						datesWithRidesCurrentMonth=facade.getThisMonthDatesWithRides(from,to,jCalendar1.getDate());
						paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);
						
						tableRides.getColumnModel().getColumn(0).setPreferredWidth(130);
						tableRides.getColumnModel().getColumn(1).setPreferredWidth(20);
						tableRides.getColumnModel().getColumn(1).setPreferredWidth(20);
						tableRides.getColumnModel().getColumn(1).setPreferredWidth(65);
					} else {
						jLabelEvents.setText("Idatzi bidaiaren jatorria eta helburua");
					}
				} catch(NullPointerException ex) {
					;
				}
			}
		});
		btnFindRides.setBounds(116, 115, 122, 21);
		
		getContentPane().add(btnFindRides);
		lblOrigin.setBounds(10, 60, 75, 13);
		
		getContentPane().add(lblOrigin);
		lblDestination.setBounds(10, 89, 75, 13);
		
		getContentPane().add(lblDestination);

	}
		
	public static void paintDaysWithEvents(JCalendar jCalendar,List<Date> datesWithEventsCurrentMonth, Color color) {
		//		// For each day with events in current month, the background color for that day is changed to cyan.


		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;


		for (Date d:datesWithEventsCurrentMonth){

			calendar.setTime(d);


			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
			//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(color);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);


	}
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
