package uo.sdi.persistence;

import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class SeatFinder {
    public static Seat find(User user, Trip trip) {
        return Jpa.getManager()
                .createNamedQuery("Seat.find", Seat.class)
                .setParameter(1, user)
                .setParameter(2, trip)
                .getSingleResult();
    }

}
