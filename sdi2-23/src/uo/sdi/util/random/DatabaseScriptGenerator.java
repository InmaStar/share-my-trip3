package uo.sdi.util.random;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

public class DatabaseScriptGenerator {
    private static final String USER_TEMPLATE = "INSERT INTO TUSERS VALUES"
	    + "(%d, '%s', '%s', '%s', '%s', %d, '%s');";
    private static final String TRIP_TEMPLATE = "INSERT INTO TTRIPS VALUES"
	    + "(%d, '%tY-%<tm-%<td %<tH:%<tM:0.000000', %d, "
	    + "'%tY-%<tm-%<td %<tH:%<tM:0.000000', '%s', '%s', '%s', '%s', "
	    + "'%s', %e, %e, '%s', "
	    + "'%tY-%<tm-%<td %<tH:%<tM:0.000000', '%s', '%s', '%s', '%s', "
	    + "%e, %e, '%s', %e, %d, %d, %d;";
    private static Locale locale = Locale.ENGLISH;
    private static PrintWriter writer;

    private static String formatUser(UserDTO user) {
	return String.format(locale, USER_TEMPLATE, user.getId(), user.getEmail(),
		user.getLogin(), user.getName(), user.getPassword(),
		user.getStatus().ordinal(), user.getSurname());
    }

    private static String formatTrip(TripDTO trip) {
	return String.format(locale, TRIP_TEMPLATE, trip.getId(), trip.getArrivalDate(),
		trip.getAvailablePax(), trip.getClosingDate(), 
		trip.getComments(), trip.getDeparture().getAddress(), 
		trip.getDeparture().getCity(), trip.getDeparture().getCountry(),
		trip.getDeparture().getState(), trip.getDeparture().getLat(),
		trip.getDeparture().getLon(), trip.getDeparture().getZipCode(),
		trip.getDepartureDate(), trip.getDestination().getAddress(),
		trip.getDestination().getCity(), 
		trip.getDestination().getCountry(), 
		trip.getDestination().getState(), 
		trip.getDestination().getLat(), trip.getDestination().getLon(),
		trip.getDestination().getZipCode(), trip.getEstimatedCost(),
		trip.getMaxPax(), trip.getStatus().ordinal(), 
		trip.getPromoter().getId());
    }

    public static void main(String[] args) throws FileNotFoundException, 
    						UnsupportedEncodingException {
	UserDTO user;
	writer = new PrintWriter("src/uo/sdi/util/random/DB", "UTF-8");

	// 3 users
	for (int i = 0; i < 3; i++) {
	    user = RandomUserGenerator.generateUser();
	    writer.println(formatUser(user));
	    
	    //10 trips per user
	    for(int j = 0; j<10; j++){
		writer.println(formatTrip(
			RandomTripGenerator.generateTrip(user)));
	    }
	}

	// test user
	writer.println(formatUser(RandomUserGenerator.generateTestUser()));
	
	writer.close();
    }

}
