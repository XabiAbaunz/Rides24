package domain;

import javax.persistence.Entity;

@Entity
public class Erreklamazio {

	private boolean konponduta;
	private boolean erantzunda;
	private String gidariMezua;
	private String bidaiariMezua;
	private Ride ride;
	private Traveler traveler;
	/* private Admin admin; */
	
	public Erreklamazio(String mezua, User user, Ride ride, User bestea) {
		this.konponduta = false;
		this.erantzunda = false;
		this.ride = ride;
		if(user instanceof Driver) {
			this.gidariMezua = mezua;
			this.traveler = (Traveler) bestea;
			this.bidaiariMezua = "";
		} else if(user instanceof Traveler) {
			this.bidaiariMezua = mezua;
			this.traveler = (Traveler) user;
			this.gidariMezua = "";
		}
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public boolean isKonponduta() {
		return konponduta;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public void setKonponduta(boolean konponduta) {
		this.konponduta = konponduta;
	}

	public boolean isErantzunda() {
		return erantzunda;
	}

	public void setErantzunda(boolean erantzunda) {
		this.erantzunda = erantzunda;
	}

	public String getGidariMezua() {
		return gidariMezua;
	}

	public void setGidariMezua(String gidariMezua) {
		this.gidariMezua = gidariMezua;
	}

	public String getBidaiariMezua() {
		return bidaiariMezua;
	}

	public void setBidaiariMezua(String bidaiariMezua) {
		this.bidaiariMezua = bidaiariMezua;
	}

	@Override
	public String toString() {
		return "Erreklamazioa: " + this.ride;
	}
}
