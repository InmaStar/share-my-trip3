package uo.sdi.persistence;

import uo.sdi.model.Trip;
import uo.sdi.persistence.util.Jpa;

import java.util.List;

public class TripFinder {
    public static Trip findById(Long id) {
        return Jpa.getManager()
                .find(Trip.class, id);
    }

    public static List<Trip> findAll() {
        return Jpa.getManager()
                .createNamedQuery("Trip.findAll", Trip.class)
                .getResultList();
    }
}
