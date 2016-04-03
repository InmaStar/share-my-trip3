package uo.sdi.util.random;

import java.util.Calendar;
import java.util.Date;

import uo.sdi.model.types.TripStatus;
import uo.sdi.transport.AddressPointDTO;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

public class RandomTripGenerator {

    private static int trips = 0;
    private static final Long TRIP_ID_INIT_VALUE = 100L;
    private static final int MIN_PAX = 1;
    private static final int MAX_PAX = 4;
    private static final double MAX_LAT = 90;
    private static final double MIN_LAT = -90;
    private static final double MAX_LON = 180;
    private static final double MIN_LON = -180;
    private static final int MIN_ZIPCODE = 99999;
    private static final int MAX_ZIPCODE = 00000;
    private static final double MAX_COST = 100;
    private static final double MIN_COST = 10;
    
    private static String[][] ciudades = { {"Oviedo", "Asturias", "España"}, 
	{"Valencia", "Comunidad Valenciana", "España"}, 
	{"Madrid", "Madrid", "España"}, {"Barcelona", "Cataluña", "España"}
    };
    private static String[] calles = {"Calle Nosecuantos", "Calle Nosequemas",
	"Avenida Avenidez", "Calle Mayor", "Avenida de la Constitucion",
	"Plaza de España"};
    
    private static Date generateRandomDate(Date before, Date after){
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(before);
	long maxDate = calendar.getTimeInMillis();
	
	calendar.setTime(after);
	long minDate = calendar.getTimeInMillis();
	
	long randDate = minDate + (long)(Math.random() * (maxDate - minDate +1));
	return new Date(randDate);
    }
    
    private static Date generateRandomDate(Date after){	
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(after);
	calendar.add(Calendar.MONTH, 2); 
	return generateRandomDate(calendar.getTime(), after);
    }
    
    private static AddressPointDTO generateRandomAddressPoint(){
	AddressPointDTO address =  new AddressPointDTO();
	
	double lat = MIN_LAT + (Math.random() * (MAX_LAT - MIN_LAT +1));
	lat = Math.round(lat * 10.0) / 10.0; //redondear a 1 decimal
	double lon = MIN_LON + (Math.random() * (MAX_LON - MIN_LON +1));
	lon = Math.round(lon * 10.0) / 10.0; //redondear a 1 decimal
	address.setLat(lat);
	address.setLon(lon);
	
	String[] ciudad = ciudades[(int) (Math.random()*ciudades.length)];
	String calle = calles[(int) (Math.random()*calles.length)];
	Integer zipcode = MIN_ZIPCODE + (int)(Math.random() * (MAX_ZIPCODE - MIN_ZIPCODE +1));
	address.setCity(ciudad[0]);
	address.setState(ciudad[1]);
	address.setCountry(ciudad[2]);
	address.setAddress(calle);
	address.setZipCode(zipcode.toString());
	
	return address;
    }
    
    public static TripDTO generateTrip() {
	TripDTO newTrip = new TripDTO();
	
	Date closingDate = generateRandomDate(Calendar.getInstance().getTime());
	Date departureDate = generateRandomDate(closingDate);
	Date arrivalDate = generateRandomDate(departureDate);
	
	AddressPointDTO departure = generateRandomAddressPoint();
	AddressPointDTO destination = generateRandomAddressPoint();
	
	int maxPax = MIN_PAX + (int)(Math.random() * (MAX_PAX - MIN_PAX +1));
	double cost = MIN_COST + (Math.random() * (MAX_COST - MIN_COST +1));
	cost = Math.round(cost * 100.0) / 100.0; //redondear a dos decimales
	
	Long tripId = newTripId();
	newTrip.setId(tripId);
	
	newTrip.setArrivalDate(arrivalDate);
	newTrip.setDepartureDate(departureDate);
	newTrip.setClosingDate(closingDate);
	
	newTrip.setMaxPax(maxPax);
	newTrip.setAvailablePax(maxPax);
	newTrip.setEstimatedCost(cost);
	
	newTrip.setDeparture(departure);
	newTrip.setDestination(destination);
	
	newTrip.setStatus(TripStatus.OPEN);
	newTrip.setComments("");
	
	return newTrip;
    }

    private static Long newTripId(){
	return ++trips + TRIP_ID_INIT_VALUE;
    }

    public static TripDTO generateTrip(UserDTO userDTO) {
	TripDTO newTrip = generateTrip();
	newTrip.setPromoter(userDTO);
	return newTrip;
    }
    
}
