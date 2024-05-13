package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alerta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String from;
	private String to;
	private Date date;
	private Traveler traveler;
	private boolean aktibatuta;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Ride> erakutsitakoBidaiak;

	public Alerta(String from, String to, Date date, Traveler traveler) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.traveler = traveler;
		this.aktibatuta = true;
		erakutsitakoBidaiak = new ArrayList<>();
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}
	
	public boolean isAktibatuta() {
		return aktibatuta;
	}

	public void setAktibatuta(boolean aktibatuta) {
		this.aktibatuta = aktibatuta;
	}
	
	public void addErakutsitakoBidaia(Ride ride) {
		this.erakutsitakoBidaiak.add(ride);
	}
	
	public ArrayList<Ride> getErakutsitakoBidaiak() {
		return erakutsitakoBidaiak;
	}

	public void setErakutsitakoBidaiak(ArrayList<Ride> erakutsitakoBidaiak) {
		this.erakutsitakoBidaiak = erakutsitakoBidaiak;
	}
	
	public long getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
		Date data1 = this.getDate();
		boolean b = false;
		Alerta a = (Alerta) o;
		Date data2 = a.getDate();
		if(o != null) {
			if(a.from==this.from && a.to == this.to && data1.getYear() == data2.getYear() && data1.getMonth() == data2.getMonth() && data1.getDay() == data2.getDay()) {
				b = true;
			}
		}
		return b;
	}
	
	@Override
	public String toString() {
		return this.from + ", " + this.to + ": "+ (date.getYear()+1900) + "/" + (date.getMonth()+1) + "/" + date.getDate();
	}
}
