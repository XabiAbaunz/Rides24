package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Administratzailea extends User implements Serializable {

	public Administratzailea(String email, String name, String password, Double cash) {
		super(email, name, password, cash);
	}

}
