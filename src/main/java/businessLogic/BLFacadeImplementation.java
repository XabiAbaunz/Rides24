package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.Traveler;
import domain.User;
import domain.Alerta;
import domain.Car;
import domain.Driver;
import domain.Erreklamazio;
import domain.ReserveStatus;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;
import exceptions.UserAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail, Car car ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail, car);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    
    public User isLogged(String user, String password) {
    	dbManager.open();
    	User u = dbManager.isLogged(user, password);
    	dbManager.close();
    	return u;
    }
    
    public User register(String email, String name, String password, double cash, String type) {
    	User u = null;
    	if(type.equals("Driver")) {
    		u = new Driver(email, name, password, cash);
    	} else if(type.equals("Traveler")) {
    		u = new Traveler(email, name, password, cash);
    	}
    	try {
    		dbManager.open();
    		dbManager.storeUser(u);
        	dbManager.close();
        	return u;
    	}
    	catch(UserAlreadyExistException e) {
    		return null;
    	}
    }
    
    public User updateMoneyByEmail(String email, double cash) {
        dbManager.open();
        User u = dbManager.updateMoneyByEmail(email, cash);
        dbManager.close();
        return u;
    }
    
    public boolean addReserve(ReserveStatus rs, int rideNumber) {
    	dbManager.open();
    	boolean e = dbManager.addReserve(rs, rideNumber);
    	dbManager.close();
    	return e;
    }
    
    public List<Ride> getAllRidesFromEmail(String email) {
    	dbManager.open();
    	List<Ride> l = dbManager.getAllRidesFromEmail(email);
    	dbManager.close();
    	return l;
    }

    public List<ReserveStatus> getAllReservesFromEmail(String email){
    	dbManager.open();
    	List<ReserveStatus> l = dbManager.getAllReservesFromEmail(email);
    	dbManager.close();
    	return l;
    }
        
    public void removeReserve(int rideNumber, int reserveNumber) {
    	dbManager.open();
    	dbManager.removeReserve(rideNumber, reserveNumber);
    	dbManager.close();
    }
    
    public boolean addRideByEmail(String email, int rideNumber) {
    	dbManager.open();
    	boolean b = dbManager.addRideByEmail(email, rideNumber);
    	dbManager.close();
    	return b;
    }
    
    public boolean addCarByEmail(String email, String marka, String modeloa, int eserlekuKop) {
    	dbManager.open();
    	Boolean b = dbManager.addCarByEmail(email, marka, modeloa, eserlekuKop);
    	dbManager.close();
    	return b;
    }
    
    public void deleteRideByRideNumber(int rideNumber) {
    	dbManager.open();
    	dbManager.deleteRideByRideNumber(rideNumber);
    	dbManager.close();
    }
    
    public List<Car> getCarsByEmail(String email) {
    	dbManager.open();
    	List<Car> cars = dbManager.getCarsByEmail(email);
		dbManager.close();
		return cars;
	}
	
    public Car getCar(String marka, String modeloa, Driver driver) {
    	dbManager.open();
    	Car car = dbManager.getCar(marka, modeloa, driver);
    	dbManager.close();
    	return car;
    }
    
    public void changeReserveStatus(ReserveStatus erreserba, Boolean erantzun, Boolean onartu) {
    	dbManager.open();
    	dbManager.changeReserveStatus( erreserba,  erantzun,  onartu);
    	dbManager.close();
    }
    
    public void bidaiaBaieztatu(String email, int reserveNumber) {
    	dbManager.open();
    	dbManager.bidaiaBaieztatu(email, reserveNumber);
    	dbManager.close();
    }
    
	public List<ReserveStatus> getAllReservesFromRideNumber(int rideNumber) {
    	dbManager.open();
    	List<ReserveStatus> reserveList = dbManager.getAllReservesFromRideNumber(rideNumber);
    	dbManager.close();
    	return reserveList;
    }
	
	public void addBalorazioByEmail(String email, double balorazioa) {
		dbManager.open();
		dbManager.addBalorazioByEmail(email, balorazioa);
		dbManager.close();
	}
	
	public void bidaiaErreklamatu(String mezua, String email, int rideNumber, String besteEmail) {
		dbManager.open();
		dbManager.bidaiaErreklamatu(mezua, email, rideNumber, besteEmail);
		dbManager.close();
	}
	
	public double getBalorazioa(String email) {
		dbManager.open();
		double balorazioa = dbManager.getBalorazioa(email);
		dbManager.close();
		return balorazioa;
	}
	
	public List<Erreklamazio> getAllErreklamazioFromEmail(String email) {
		dbManager.open();
		List<Erreklamazio> erreklamazioak = dbManager.getAllErreklamazioFromEmail(email);
		dbManager.close();
		return erreklamazioak;
	}
	
	public List<Erreklamazio> getAllErreklamazioFromRideNumber(int rideNumber) {
		dbManager.open();
		List<Erreklamazio> erreklamazioak = dbManager.getAllErreklamazioFromRideNumber(rideNumber);
		dbManager.close();
		return erreklamazioak;
	}
	
	public void erreklamazioaErantzun(String email, int rideNumber, boolean onartuta, String arrazoia) {
		dbManager.open();
		dbManager.erreklamazioaOnartu(email, rideNumber, onartuta, arrazoia);
		dbManager.close();
	}
	
	public void deskontuaSortu(String kodea, int zenbatekoa, Date iraunData) {
		dbManager.open();
		dbManager.deskontuaSortu(kodea, zenbatekoa, iraunData);
		dbManager.close();
		
	}
	
	public int deskontuaEgiaztatu(String kodea, String email) {
		dbManager.open();
		int kop = dbManager.deskontuaEgiaztatu(kodea, email);
		dbManager.close();
		return kop;
	}
	
	public Erreklamazio getKonponduGabekoErreklamazioa() {
		dbManager.open();
		Erreklamazio erreklamazioa = dbManager.getKonponduGabekoErreklamazioa();
		dbManager.close();
		return erreklamazioa;
	}
	
	public void erreklamazioaKonpondu(String nork, int rideNumber, String tEmail) {
		dbManager.open();
		dbManager.erreklamazioaKonpondu(nork, rideNumber, tEmail);
		dbManager.close();
	}
	
	public void addAlertaByEmail(String email, String from, String to, Date date) {
		dbManager.open();
		dbManager.addAlertaByEmail(email, from, to, date);
		dbManager.close();
	}
	
	public List<Alerta> alertaSortuDa(String email) {
		dbManager.open();
		List<Alerta> alertak = dbManager.alertaSortuDa(email);
		dbManager.close();
		return alertak;
	}
}