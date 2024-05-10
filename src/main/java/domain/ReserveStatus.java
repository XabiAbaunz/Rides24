package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ReserveStatus {
	
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int reserveNumber;
	private boolean accepted=false;
	private boolean answered=false;
	private boolean finished=false;
	private double frozenBalance;
	private Traveler traveler;
	private Ride bidaia;
	private static int count = 0;
	
	public boolean isFinished() {
		return this.finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public int getReserveNumber() {
		return reserveNumber;
	}

	public void setReserveNumber(int reserveNumber) {
		this.reserveNumber = reserveNumber;
	}

	public ReserveStatus(float price, Traveler traveler, Ride bidaia) {
		this.frozenBalance = price;
		this.traveler = traveler;
		this.bidaia = bidaia;
		reserveNumber = count;
		count++;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	public double getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(double frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public Traveler getTraveler() {
		return traveler;
	}

    public Ride getRide() {
        return bidaia;
    }

    public void setRide(Ride ride) {
        this.bidaia = ride;
    }
	public void setEmail(Traveler traveler) {
		this.traveler = traveler;
	}
	
	@Override
	public String toString() {
		return reserveNumber+";"+traveler;
	}

	public String getStatus() {
	    if (accepted && answered) {
	        return "Onartua";
	    } else if (!accepted && answered) {
	        return "Baztertua";
	    } else {
	        return "Itxaroten";
	    }
	}

}
