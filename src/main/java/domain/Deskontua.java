package domain;


import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Deskontua {
	@Id
	private String kodea;
	private Date iraungitzeData;
	private int zenbatekoa;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Traveler> erabilita;
	
	
	public Deskontua(String kodea, int zenbatekoa, Date iraunData) {
		this.kodea = kodea;
		this.iraungitzeData = iraunData;
		this.zenbatekoa = zenbatekoa;
		this.erabilita = new ArrayList<>();
	}
	
	public Date getIraungitzeData() {
		return this.iraungitzeData;
	}
	public ArrayList<Traveler> getErabilita(){
		return this.erabilita;
	}
	public int getZenbatekoa() {
		return this.zenbatekoa;
	}
	
	
}
