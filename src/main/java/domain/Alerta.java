package domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Alerta {
	
	@Override
	public String toString() {
		return "Alerta [from=" + from + ", to=" + to + ", date=" + date.getYear() + "/" + date.getMonth() + "/" + date.getDay() + "]";
	}

	private String from;
	private String to;
	private Date date;
	private Traveler traveler;
	private boolean aktibatuta;

	public Alerta(String from, String to, Date date, Traveler traveler) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.traveler = traveler;
		this.aktibatuta = true;
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
}
