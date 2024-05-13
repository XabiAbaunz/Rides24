package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Traveler extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<ReserveStatus> reserves=new Vector<ReserveStatus>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Erreklamazio> erreklamazioak = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Alerta> alertak = new ArrayList<>();
	
	public Traveler(String email, String name, String password, Double cash) {
		super(email, name, password, cash);
	}
	
	public void addReserve(ReserveStatus r)  {
        reserves.add(r);
	}
	
	public List<ReserveStatus> getReserves() {
		return this.reserves;
	}
	
	public ArrayList<Erreklamazio> getErreklamazioak() {
		return this.erreklamazioak;
	}
	
	public void addErreklamazio(Erreklamazio erreklamazio) {
		this.erreklamazioak.add(erreklamazio);
	}

	@Override
	public String toString() {
		return "Traveler " + super.toString();
	}
	
	public void addAlerta(String from, String to, Date date) {
		Alerta alerta = new Alerta(from, to, date, this);
		if(!alertak.contains(alerta)) {
			this.alertak.add(alerta);
		}
	}
	
	public ArrayList<Alerta> getAlertak() {
		return this.alertak;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Traveler other = (Traveler) obj;
		return Objects.equals(reserves, other.reserves);
	}
	
}
