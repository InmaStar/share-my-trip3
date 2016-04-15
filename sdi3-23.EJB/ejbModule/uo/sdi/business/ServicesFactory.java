package uo.sdi.business;

public interface ServicesFactory {

    UserService createUserService();

    TripService createTripService();

}
