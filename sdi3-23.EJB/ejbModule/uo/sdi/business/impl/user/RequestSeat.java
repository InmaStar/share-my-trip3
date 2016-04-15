package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.TripFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import javax.persistence.NoResultException;

public class RequestSeat implements Command<UserDTO> {
    private UserDTO userDTO;
    private Long tripId;

    public RequestSeat(UserDTO user, TripDTO trip) {
        this.userDTO = user;
        this.tripId = trip.getId();
    }

    @Override
    public UserDTO execute() throws BusinessException {
        try {
            User user = UserFinder.findByLogin(userDTO.getLogin());
            Trip trip = TripFinder.findById(tripId);
            user.requestSeat(trip);
            return new UserDTO(user);
        } catch (NoResultException e) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }
}
