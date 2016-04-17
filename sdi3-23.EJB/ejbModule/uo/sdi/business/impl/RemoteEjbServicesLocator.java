package uo.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripService;
import uo.sdi.business.UserService;

public class RemoteEjbServicesLocator implements ServicesFactory {

    private static final String TRIP_SERVICE_JNDI_KEY = 
	    "sdi3-23/" 
	    + "sdi3-23.EJB/" 
	    + "EjbTripService!"
	    + "uo.sdi.business.impl.RemoteTripService";

    private static final String USER_SERVICE_JNDI_KEY = 
	    "sdi3-23/" 
	    + "sdi3-23.EJB/" 
	    + "EjbUserService!"
	    + "uo.sdi.business.impl.RemoteUserService";

    @Override
    public UserService getUserService() {
	try {
	    Context ctx = new InitialContext();
	    return (UserService) ctx.lookup(USER_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public TripService getTripService() {
	try {
	    Context ctx = new InitialContext();
	    return (TripService) ctx.lookup(TRIP_SERVICE_JNDI_KEY);
	} catch (NamingException e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }


}
