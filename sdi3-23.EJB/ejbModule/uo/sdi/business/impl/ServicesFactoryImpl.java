package uo.sdi.business.impl;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripService;
import uo.sdi.business.UserService;

public class ServicesFactoryImpl implements ServicesFactory {
    @Override
    public UserService createUserService() {
        return new EjbUserService();
    }

    @Override
    public TripService createTripService() {
        return new EjbTripService();
    }
}
