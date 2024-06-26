package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public abstract class User {

	@XmlID
	@Id 
	private String email;
	private String name;
	private String password;
	private Double cash;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Movement> movements;
	
	public User(String email, String name, String password, Double cash) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.cash = cash;
		this.movements = new ArrayList<Movement>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "[email=" + email + ", name=" + name + ", password=" + password + ", cash=" + cash + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cash, other.cash) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}
	
	public ArrayList<Movement> getMovements() {
		return movements;
	}

	public void setMovements(ArrayList<Movement> movement) {
		this.movements = movement;
	}

	public void addMovement(double diruKop, Date data) {
		this.movements.add(new Movement(this, diruKop, data));
	}		
	
}
