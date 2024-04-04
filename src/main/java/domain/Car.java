package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Car {

	private Driver driver;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Ride> rides;
	private String marka;
	private int eserlekuKop;
	private String modeloa;
	
	public Car(Driver driver, String marka, String modeloa, int eserlekuKop) {
		super();
		this.driver = driver;
		this.marka = marka;
		this.modeloa = modeloa;
		this.eserlekuKop = eserlekuKop;
		this.rides = new ArrayList<>();
	}
	
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public ArrayList<Ride> getRides() {
		return rides;
	}

	public void setRides(ArrayList<Ride> rides) {
		this.rides = rides;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public int getEserlekuKop() {
		return eserlekuKop;
	}

	public void setEserlekuKop(int eserlekuKop) {
		this.eserlekuKop = eserlekuKop;
	}

	public String getModeloa() {
		return modeloa;
	}

	public void setModeloa(String modeloa) {
		this.modeloa = modeloa;
	}
	
	@Override
	public String toString() {
		return "[marka=" + marka + ", modeloa=" + modeloa + ", eserlekuKop=" + eserlekuKop + "]";
	}

	public Ride addRide(String from, String to,  Date date, int nPlaces, float price) {
		Ride ride=new Ride(from,to,date,nPlaces,price, this);
		rides.add(ride);
		return ride;
	}
	
	public boolean doesRideExists(String from, String to, Date date)  {	
		for (Ride r:rides)
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			 return true;
		
		return false;
	}
	
	public Ride removeRide(String from, String to, Date date) {
		boolean found=false;
		int index=0;
		Ride r=null;
		while (!found && index<=rides.size()) {
			r=rides.get(++index);
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			found=true;
		}
			
		if (found) {
			rides.remove(index);
			return r;
		} else return null;
	}
	
}
