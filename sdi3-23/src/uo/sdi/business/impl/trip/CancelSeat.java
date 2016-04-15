package uo.sdi.business.impl.trip;

import javax.persistence.NoResultException;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.types.TravelStatus;
import uo.sdi.persistence.TripFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.transport.TripDTO;

public class CancelSeat implements Command<TripDTO> {

    private Long userId;
    private Long tripId;

    public CancelSeat(Long userId, TripDTO trip) {
        this.userId = userId;
        this.tripId = trip.getId();
    }

    @Override
    public TripDTO execute() throws BusinessException {
        try {
            User user = UserFinder.findById(userId);
            Trip trip = TripFinder.findById(tripId);
            Seat seat = user.getSeatByTrip(trip);
            seat.setStatus(TravelStatus.EXCLUDED);
            trip.setAvailablePax(trip.getAvailablePax()+1);
            return new TripDTO(trip);
        } catch (NoResultException e) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }
}
