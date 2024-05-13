package dataAccess;

import java.io.File;
import java.net.NoRouteToHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.UserAlreadyExistException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized())initializeDB();
		
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		

		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=1; year+=1;}  
	    
		 //Gidariak
		   Driver driver1 =new Driver("a","a","a", 100.00);
		   Driver driver2=new Driver("b","b","b", 100.00);
		   db.persist(driver1);
		   db.persist(driver2);
		   
		   //Bidaiariak
		   Traveler traveler1 = new Traveler("d","d","d", 100.00);
		   Traveler traveler2 = new Traveler("e","e","e", 100.00);
		   db.persist(traveler1);
		   db.persist(traveler2);
		   
		   //Administratzaileak
		   Administratzailea admin1 = new Administratzailea("c","c","c",10.0);
		   Administratzailea admin2 = new Administratzailea("f","f","f",10.0);
		   db.persist(admin1);
		   db.persist(admin2);
		   
		   
		   
		   
		   //Kotxe bat gidari bakoitzari
		   Car kotxe1 = new Car(driver1, "opel", "corsa", 4);
		   Car kotxe2 = new Car(driver2, "citroen", "xantia", 3);
		   db.persist(kotxe1);
		   db.persist(kotxe2);
		   
		   
		   //Bi bidaia kotxe bakoitzak
		   Ride bidaia1 = new Ride("Azpeitia", "Donostia", UtilDate.newDate(year,month,15) , 4, 8, kotxe1);
		   Ride bidaia2 = new Ride("Malaga", "Cadiz", UtilDate.newDate(year,month,28) , 4, 20, kotxe1);
		   Ride bidaia3 = new Ride("Hernani", "Tolosa", UtilDate.newDate(year,month,06) , 3, 5, kotxe2);
		   Ride bidaia4 = new Ride("Lisboa", "Paris", UtilDate.newDate(year,month,20) , 3, 100, kotxe2);
		   db.persist(bidaia1);
		   db.persist(bidaia2);		   
		   db.persist(bidaia3);		   
		   db.persist(bidaia4);
		    /*Create drivers 
			Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez");
			Driver driver2=new Driver("driver2@gmail.com","Ane Gaztañaga");
			Driver driver3=new Driver("driver3@gmail.com","Test driver");

			
			//Create rides
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year,month,6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 4, 4);

			driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year,month,7), 4, 8);
			
			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year,month,6), 2, 5);

			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,14), 1, 3);
			
			
						
			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);
			*/
	
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
			List<String> cities = query.getResultList();
			return cities;
		
	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from){
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList(); 
		return arrivingCities;
		
	}
	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail, Car car) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= "+from+" to= "+to+" driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			List<Ride> res = new ArrayList<>();	
			TypedQuery<Car> query = db.createQuery("SELECT c FROM Car c WHERE c.marka=?1 AND c.modeloa=?2 AND c.eserlekuKop=?3 AND c.driver=?4",Car.class);   
			query.setParameter(1, car.getMarka());
			query.setParameter(2, car.getModeloa());
			query.setParameter(3, car.getEserlekuKop());
			query.setParameter(4, car.getDriver());
			List<Car> rides = query.getResultList();
			Car c = rides.get(0);
			if (c.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = c.addRide(from, to, date, nPlaces, price);
			//next instruction can be obviated
			db.persist(c); 
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
		
	}
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Ride> res = new ArrayList<>();	
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);   
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
		   res.add(ride);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",Date.class);   
		
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}
	
	public User isLogged(String user, String password) {
		User u = db.find(User.class, user);
		if(u != null && u.getPassword().equals(password)) {
			System.out.println(">> DataAccess: isLogged=> erabiltzailea sisteman dago");
			return u;
		} else {
			return null;
		}
	}
	
	public void storeUser(User u) throws UserAlreadyExistException {
		System.out.println(">> DataAccess: storeUser=> " + u.toString());
		User user = db.find(User.class, u.getEmail());
		if(user == null) {
			db.getTransaction().begin();
			db.persist(u); 
			db.getTransaction().commit();
		} else {
			throw new UserAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.UserAlreadyExist"));
		}
	}
	
	public User getUserByEmail(String email) {
		return db.find(User.class,email);
	}
	
	public User updateMoneyByEmail(String email, double cash) {
		User u = getUserByEmail(email);
		db.getTransaction().begin();
		u.setCash(u.getCash() + cash);
		u.addMovement(cash, new Date());	
		db.persist(u);
		db.getTransaction().commit();
		System.out.println(u + " has been updated");
		return u;
	}
	
	public Ride getRideByRideNumber(int rideNumber) {
		return db.find(Ride.class,rideNumber);
	}
	
	public boolean addReserve(ReserveStatus rs, int rideNumber) {
		Ride ride = this.getRideByRideNumber(rideNumber);
		db.getTransaction().begin();
		boolean e = ride.addReserve(rs);
		ride.setCount(ride.getCount()+1);
		db.persist(ride);
		db.getTransaction().commit();
		System.out.println("Reserve: " + rs.getReserveNumber() + " has been added to: " + ride);
		return e;
	}  
	
	public void removeReserve(int rideNumber, int reserveNumber) {
		Ride ride = this.getRideByRideNumber(rideNumber);
		db.getTransaction().begin();
		ReserveStatus[] rlist = ride.getReserveList();
		ReserveStatus rstatus;
		System.out.println(rlist);
		for(int i = 0; i < rlist.length; i++) {
			rstatus = rlist[i];
			if(rstatus != null && rstatus.getReserveNumber()==reserveNumber) {
				rlist[i]=null;
				ride.setCount(ride.getCount()-1);
			}
		}
		DataAccess.moveNullsToRight(rlist);
		System.out.println(rlist);
		ride.setReserveList(rlist);
		db.persist(ride);
		db.getTransaction().commit();
		System.out.println("Reserve: " + reserveNumber + " has been removed");
	}
	
	public List<Ride> getAllRidesFromEmail(String email) {
		List<Ride> rideList = new ArrayList<>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.car.driver.email = ?1", Ride.class);
        query.setParameter(1, email);
		rideList = query.getResultList();
	 	return rideList;
	}
	
	public List<ReserveStatus> getAllReservesFromEmail(String email) {
		List<ReserveStatus> reserveList = new ArrayList<>();
		TypedQuery<ReserveStatus> query = db.createQuery("SELECT rs FROM ReserveStatus rs WHERE rs.traveler.email = ?1", ReserveStatus.class);
        query.setParameter(1, email);
		reserveList = query.getResultList();
	 	return reserveList;
	}
	
	public void changeReserveStatus(ReserveStatus erreserba, Boolean erantzun, Boolean onartu) {
		db.getTransaction().begin();
		erreserba.setAccepted(onartu);
		erreserba.setAnswered(erantzun);
		db.merge(erreserba);
		db.getTransaction().commit();
	}
	
	
	public boolean addRideByEmail(String email, int rideNumber) {
		Ride ride = this.getRideByRideNumber(rideNumber);
		db.getTransaction().begin();
		Traveler t = (Traveler)this.getUserByEmail(email);
		db.persist(t);
		db.getTransaction().commit();
		System.out.println("Ride: " + ride + " has been added to: " + t);
		return true;
	}  
	
	 public boolean addCarByEmail(String email, String marka, String modeloa, int eserlekuKop) {
		 db.getTransaction().begin();
		 Driver driver = (Driver) this.getUserByEmail(email);
		 System.out.println(driver.getCars());
		 driver.addCar(marka, modeloa, eserlekuKop);
		 db.persist(driver);
		 db.getTransaction().commit();
		 System.out.println("Car: " + marka + ", " + modeloa + ", " + eserlekuKop +  " has been added to: " + driver);
		 return true;
	 }
	 
	 public void deleteRideByRideNumber(int rideNumber) {
		 db.getTransaction().begin();
		 Ride ride = this.getRideByRideNumber(rideNumber);
		 if(ride != null) {
			 for(ReserveStatus rs:ride.getReserveList()) {
				 if(rs != null) {
					 Traveler t = rs.getTraveler();
					 t.getReserves().remove(rs);
					 db.persist(t);
					 db.remove(rs);
				 }
			 }
			 Car c = ride.getCar();
			 c.getRides().remove(ride);
			 db.persist(c);
			 db.remove(ride);
			 db.getTransaction().commit();
			 System.out.println("Ride: " + ride + " has been deleted.");
	 	} else {
	 		System.out.println("The ride doesn't exist");
	 	}
	 }
	 
	 public List<Car> getCarsByEmail(String email) {
		 List<Car> carList = new ArrayList<Car>();
		 TypedQuery<Car> query = db.createQuery("SELECT c FROM Car c WHERE c.driver.email = ?1", Car.class);
	     query.setParameter(1, email);
		 carList = query.getResultList();
		 return carList;
	 }
	 
	 public Car getCar(String marka, String modeloa, Driver driver) {
		 List<Car> carList = new ArrayList<Car>();
		 TypedQuery<Car> query = db.createQuery("SELECT c FROM Car c WHERE c.marka = ?1 AND c.modeloa = ?2 AND c.driver = ?3", Car.class);
	     query.setParameter(1, marka);
	     query.setParameter(2, modeloa);
	     query.setParameter(3, this.getDriverByEmail(driver.getEmail()));
		 carList = query.getResultList();
		 return carList.get(0);
	 }
	 
	 public Driver getDriverByEmail(String email) {
		 return db.find(Driver.class,email);
	 }
	 
	public void bidaiaBaieztatu(String email, int reserveNumber){
		db.getTransaction().begin();
		ReserveStatus erreserba = db.find(ReserveStatus.class, reserveNumber);
		if(erreserba != null) {
			Ride bidaia = erreserba.getRide();
			double dirua = erreserba.getFrozenBalance();
			Driver gidaria = bidaia.getCar().getDriver();
			gidaria.setCash(gidaria.getCash() + dirua);
			Date data = new Date();
			gidaria.addMovement(dirua, data);
	        db.persist(gidaria);
			db.getTransaction().commit();
	        removeReserve(bidaia.getRideNumber(), reserveNumber);
	        db.getTransaction().begin();
	        db.remove(erreserba);
	        boolean allReservesFinished = true;
	        for (ReserveStatus rs : bidaia.getReserveList()) {
	            if (rs != null && !rs.isFinished()) {
	                allReservesFinished = false;
	                break;
	            }
	        }
	        if (allReservesFinished) {
	            bidaia.setBukatuta(true);
	        }
		}
		db.getTransaction().commit();
	}
	
	public List<ReserveStatus> getAllReservesFromRideNumber(int rideNumber) {
		List<ReserveStatus> reserveList = new ArrayList<ReserveStatus>();
		TypedQuery<ReserveStatus> query = db.createQuery("SELECT rs FROM ReserveStatus rs WHERE rs.bidaia.rideNumber = ?1", ReserveStatus.class);
	    query.setParameter(1, rideNumber);
	    reserveList = query.getResultList();
	    return reserveList;
	}
	
	public void addBalorazioByEmail(String email, double balorazioa) {
		Driver driver = db.find(Driver.class, email);
		 db.getTransaction().begin();
		 driver.addBalorazio(balorazioa);
		 db.persist(driver);
		 db.getTransaction().commit();
		 System.out.println("Balorazioa: " + balorazioa + " has been added to " + email + ".");
	}
	
	public void bidaiaErreklamatu(String arrazoia, String email, int rideNumber, String besteEmail) {
		Erreklamazio err = null;
		User u = this.getUserByEmail(email);
		Ride r = this.getRideByRideNumber(rideNumber);
		Traveler t = null;
		if(u instanceof Driver) {
			t = db.find(Traveler.class, besteEmail);
		}
		db.getTransaction().begin();
		err = new Erreklamazio(arrazoia, u, r, t);
		r.addErreklamazio(err);
		if(u instanceof Driver) {
			t.addErreklamazio(err);;
			db.persist(t);
		}
		db.persist(r);
		db.getTransaction().commit();
		System.out.println("New Erreklamazio has been added to Ride: " + r.getRideNumber());
	}
	
	public double getBalorazioa(String email) {
		 Driver gidaria = db.find(Driver.class, email);
		 ArrayList<Double> balorazioak = gidaria.getBalorazioak();
		 double guztira = 0.0;
		 if	(balorazioak.size() != 0) {
	        for (Double balorazioa : balorazioak) {
	            guztira += balorazioa;
	        }
	        return guztira / balorazioak.size();
		 }else {
	        	return -1;
	        }
	}
	
	public List<Erreklamazio> getAllErreklamazioFromEmail(String email) {
		Traveler traveler = (Traveler) this.getUserByEmail(email);
		return traveler.getErreklamazioak();
	}
	
	public List<Erreklamazio> getAllErreklamazioFromRideNumber(int rideNumber) {
		Ride ride = db.find(Ride.class, rideNumber);
		return ride.getErreklamazioak();
	}
	
	public void erreklamazioaOnartu(String email, int rideNumber, boolean onartuta, String arrazoia) {
		List<Erreklamazio> erreklamazioList = new ArrayList<Erreklamazio>();
		TypedQuery<Erreklamazio> query = db.createQuery("SELECT err FROM Erreklamazio err WHERE err.traveler.email = ?1 AND err.ride.rideNumber = ?2", Erreklamazio.class);
	    query.setParameter(1, email);
	    query.setParameter(2, rideNumber);
	    erreklamazioList = query.getResultList();
	    Erreklamazio err = erreklamazioList.get(0);
	    db.getTransaction().begin();
	    err.setErantzunda(true);
	    err.setKonponduta(onartuta);
	    if(!onartuta && err.getGidariMezua().equals("")) {
	    	err.setGidariMezua(arrazoia);
	    } else if(!onartuta && err.getBidaiariMezua().equals("")) {
	    	err.setBidaiariMezua(arrazoia);
	    }
	    db.persist(err);
	    db.getTransaction().commit();
	}
	
	public void deskontuaSortu(String kodea, int zenbatekoa, Date iraunData) {
		db.getTransaction().begin();
		Deskontua deskontu = new Deskontua(kodea, zenbatekoa, iraunData);
		db.persist(deskontu);
		db.getTransaction().commit();	
	}
	
	public int deskontuaEgiaztatu(String kodea, String email) {
		Deskontua desk = db.find(Deskontua.class, kodea);
		//Deskontua ez da aurkitu
		if(desk == null) {return -1;}
		//Deskontua kadukatuta
		if(desk.getIraungitzeData().before(new Date())) {return -2;}
		//Deskontua erabiltzaileak iada erabilita
		for(Traveler bidaiari : desk.getErabilita()) {
			if(bidaiari.getEmail().equals(email)) {return -3;}
		}
		Traveler bidaiaria = db.find(Traveler.class, email);
		desk.getErabilita().add(bidaiaria);
		db.getTransaction().begin();
		db.persist(desk);
		db.getTransaction().commit();
		return desk.getZenbatekoa();
	}
	
	public void deskontuaErabili(String kodea, String email) {
		Traveler bidaiaria = db.find(Traveler.class, email);
		Deskontua desk = db.find(Deskontua.class, kodea);
		desk.getErabilita().add(bidaiaria);
		db.getTransaction().begin();
		db.persist(desk);
		db.getTransaction().commit();	
	}
	
	public Erreklamazio getKonponduGabekoErreklamazioa() {
		List<Erreklamazio> erreklamazioList = new ArrayList<Erreklamazio>();
		TypedQuery<Erreklamazio> query = db.createQuery("SELECT err FROM Erreklamazio err WHERE err.konponduta = false", Erreklamazio.class);
	    erreklamazioList = query.getResultList();
	    Erreklamazio err = erreklamazioList.get(0);
	    return err;
	}
	
	public void erreklamazioaKonpondu(String nork, int rideNumber, String email) {
		List<Erreklamazio> erreklamazioList = new ArrayList<Erreklamazio>();
		TypedQuery<Erreklamazio> query = db.createQuery("SELECT err FROM Erreklamazio err WHERE err.traveler.email = ?1 AND err.ride.rideNumber = ?2", Erreklamazio.class);
	    query.setParameter(1, email);
	    query.setParameter(2, rideNumber);
	    erreklamazioList = query.getResultList();
	    Erreklamazio err = erreklamazioList.get(0);
	    db.getTransaction().begin();
	    err.setKonponduta(true);
    	ReserveStatus erreserba = null;
    	List<ReserveStatus> erreserbak = this.getAllReservesFromRideNumber(rideNumber);
    	for(ReserveStatus rs:erreserbak) {
    		if(rs.getTraveler().getEmail().equals(email)) {
    			erreserba = rs;
    			break;
    		}
    	}
	    if(nork.equals("g")) {
	    	this.updateMoneyByEmail(erreserba.getRide().getCar().getDriver().getEmail(), erreserba.getFrozenBalance());
	    } else if(nork.equals("b")) {
	    	this.updateMoneyByEmail(erreserba.getTraveler().getEmail(), erreserba.getFrozenBalance());
	    }
	}
	
	public void addAlertaByEmail(String email, String from, String to, Date date) {
		Traveler traveler = (Traveler) this.getUserByEmail(email);
		db.getTransaction().begin();
		traveler.addAlerta(from, to, date);
		db.persist(traveler);
		db.getTransaction().commit();
		System.out.println("New alerta has been created.");
	}
	
	public List<Alerta> alertaSortuDa(String email) {
		boolean aurkituta = false;
		List<Alerta> itzultzekoAlertak = new ArrayList<Alerta>();
		Traveler traveler = (Traveler) this.getUserByEmail(email);
		List<Alerta> alertak = traveler.getAlertak();
		String from;
		String to;
		Date date;
		for(Alerta a:alertak) {
			from = a.getFrom();
			to = a.getTo();
			date = a.getDate();
			List<Ride> rideList = new ArrayList<Ride>();
			TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from = ?1 AND r.to = ?2", Ride.class);
		    query.setParameter(1, from);
		    query.setParameter(2, to);
		    rideList = query.getResultList();
		    if(!rideList.isEmpty()) {
			    for(Ride r:rideList) {
			    	if(r!=null) {
				    	Date data = r.getDate();
				    	if(date.getYear() == data.getYear() && date.getMonth() == data.getMonth() && date.getDate() == data.getDate() && !a.getErakutsitakoBidaiak().contains(r)) {
				    		itzultzekoAlertak.add(a);
				    		aurkituta = true;
				    		db.getTransaction().begin();
				    		a.addErakutsitakoBidaia(r);
				    		db.persist(r);
				    		db.getTransaction().commit();
				    	}
			    	}
			    }
		    }
		}
		System.out.println("Travaler has alerts: " + aurkituta);
		return itzultzekoAlertak;
	}
	
	public List<Alerta> getAlertakByEmail(String email) {
		Traveler traveler = (Traveler) this.getUserByEmail(email);
		return traveler.getAlertak();
	}
	
	public void alertaEzabatu(Long id, String email) {
		db.getTransaction().begin();
		Traveler bidaiaria = db.find(Traveler.class, email);
		Alerta alerta = db.find(Alerta.class, id);
		bidaiaria.getAlertak().remove(alerta);
		db.merge(bidaiaria);
		db.getTransaction().commit();
	}
	
	 public User getErabiltzailea(String email) {
		User u = db.find(User.class, email);
		return u;
	 }

	public void erabiltzaileaEzabatu(String email) {
		db.getTransaction().begin();
		 User user = this.getErabiltzailea(email);
		 if(user!=null) {
			 if(user instanceof Traveler) {
				 Traveler t = db.find(Traveler.class, email);
				 List<ReserveStatus> rs = t.getReserves();
				 for (ReserveStatus reserve : rs) {
					 rs.remove(rs);
				 }
			 }else if(user instanceof Driver) {
				 Driver d = db.find(Driver.class, email);
				 for(Car car: d.getCars()) {
					 List<Ride> rides = car.getRides();
					 for(Ride ride : rides) {
						 Integer rideNumber = ride.getRideNumber();
						 deleteRideByRideNumber(rideNumber);
						 db.remove(car);
					 }
				 }
				 db.remove(d);
				 db.getTransaction().commit();
			 }
		 }
	}
	
	
	
	public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());

		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
	 public static void moveNullsToRight(ReserveStatus[] array) {
	        int writeIndex = array.length - 1;

	        for (int readIndex = array.length - 1; readIndex >= 0; readIndex--) {
	            if (array[readIndex] != null) {
	                if (readIndex != writeIndex) {
	                    array[writeIndex] = array[readIndex];
	                    array[readIndex] = null;
	                }
	                writeIndex--;
	            }
	        }
	    }


}
