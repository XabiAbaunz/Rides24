package businessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Ride;
import domain.User;
import domain.Alerta;
import domain.Car;
import domain.Driver;
import domain.Erreklamazio;
import domain.ReserveStatus;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	@WebMethod public List<String> getDepartCities();
	
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	@WebMethod public List<String> getDestinationCities(String from);


	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driver to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail, Car car) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;
	
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	@WebMethod public List<Ride> getRides(String from, String to, Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	@WebMethod public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	public User isLogged(String user, String password);
	
	public User register(String email, String name, String password, double cash, String type);
	
	public User updateMoneyByEmail(String email, double cash);
	
	public boolean addReserve(ReserveStatus rs, int rideNumber);
	
	public List<Ride> getAllRidesFromEmail(String email);
	
	public void removeReserve(int rideNumber, int reserveNumber);
	
	public boolean addRideByEmail(String email, int rideNumber);
	
	public boolean addCarByEmail(String email, String marka, String modeloa, int eserlekuKop);
	
	public void deleteRideByRideNumber(int rideNumber);
	
	public List<Car> getCarsByEmail(String email);
	
	public Car getCar(String marka, String modeloa, Driver driver);

	public List<ReserveStatus> getAllReservesFromEmail(String email);
	
	public void changeReserveStatus(ReserveStatus erreserba, Boolean erantzun, Boolean onartu);
	
	public void bidaiaBaieztatu(String email, int reserveNumber);

	public List<ReserveStatus> getAllReservesFromRideNumber(int rideNumber);
	
	public void addBalorazioByEmail(String email, double balorazioa);
	
	public void bidaiaErreklamatu(String mezua, String email, int rideNumber, String besteEmail);

	public double getBalorazioa(String email);
	
	public List<Erreklamazio> getAllErreklamazioFromEmail(String email);
	
	public List<Erreklamazio> getAllErreklamazioFromRideNumber(int rideNumber);
	
	public void erreklamazioaErantzun(String email, int rideNumber, boolean onartuta, String arrazoia);
	
	public void deskontuaSortu(String kodea, int zenbatekoa, Date iraunData);
	
	public int deskontuaEgiaztatu(String kodea, String email);
	
	public void deskontuaErabili(String kodea, String email);
	
	public Erreklamazio getKonponduGabekoErreklamazioa();
	
	public void erreklamazioaKonpondu(String nork, int rideNumber, String tEmail);
	
	public void addAlertaByEmail(String email, String from, String to, Date date);
	
	public List<Alerta> alertaSortuDa(String email);
	
	public List<Alerta> getAlertakByEmail(String email);
	
	public void alertaEzabatu(Long id, String email);
	
	public User getErabiltzailea(String email);
	
	public void erabiltzaileaEzabatu (String email);

	
}
