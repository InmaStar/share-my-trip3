package uo.sdi.model.util;

import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class Association {

    public static class Promotes {
        public static void link(User user, Trip trip) {
            trip._setPromoter(user);
            user._getPromotedTrips().add(trip);
        }

        public static void unlink(Trip trip) {
            trip.getPromoter()._getPromotedTrips().remove(trip);
            trip._setPromoter(null);
        }
    }

    public static class ApplyTo {
        public static void link(User user, Trip trip) {
            user._getAppliedTrips().add(trip);
            trip._getApplicants().add(user);
        }

        public static void unlink(User user, Trip trip) {
            user._getAppliedTrips().remove(trip);
            trip._getApplicants().remove(user);
        }
    }

    public static class SitIn {
        public static void link(Seat seat, User user, Trip trip) {
            seat._setTrip(trip);
            seat._setUser(user);
            user._getSeats().add(seat);
            trip._getSeats().add(seat);
        }

        public static void unlink(Seat seat) {
            seat.getUser()._getSeats().remove(seat);
            seat.getTrip()._getSeats().remove(seat);
            seat._setUser(null);
            seat._setTrip(null);
        }
    }

    public static class Rate {
        public static void link(Rating rating, Seat from, Seat about) {
            rating._setFrom(from);
            rating._setAbout(about);
            from._getFrom().add(rating);
            about._getAbout().add(rating);
        }

        public static void unlink(Rating rating) {
            rating.getFrom()._getFrom().remove(rating);
            rating.getAbout()._getAbout().remove(rating);
            rating._setFrom(null);
            rating._setAbout(null);
        }
    }
}
