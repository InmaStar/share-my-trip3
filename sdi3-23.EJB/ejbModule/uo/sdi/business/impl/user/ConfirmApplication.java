package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.types.TravelStatus;
import uo.sdi.persistence.TripFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import javax.persistence.NoResultException;

public class ConfirmApplication implements Command<UserDTO> {
    private Long userId;
    private Long tripId;

    public ConfirmApplication(Long userId, TripDTO trip) {
        this.userId = userId;
        this.tripId = trip.getId();
    }

    @Override
    public UserDTO execute() throws BusinessException {
        try {
            User user = UserFinder.findById(userId);
            Trip trip = TripFinder.findById(tripId);
            Seat seat = user.getSeatByTrip(trip);
            if (seat == null) {
                seat = user.confirmSeat(trip);
                Jpa.getManager().persist(seat);
            } else {
                seat.setStatus(TravelStatus.ACCEPTED);
            }
            trip.setAvailablePax(trip.getAvailablePax()-1);
            return new UserDTO(user, false);
        } catch (NoResultException e) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }
}
