package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.InvalidActionException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.exception.TripNotPendingException;
import uo.sdi.persistence.TripFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import javax.persistence.NoResultException;

public class CancelApplication implements Command<UserDTO> {
    private UserDTO userDTO;
    private Long tripId;

    public CancelApplication(UserDTO user, TripDTO trip) {
        this.userDTO = user;
        this.tripId = trip.getId();
    }

    @Override
    public UserDTO execute() throws BusinessException {
        try {
            User user = UserFinder.findByLogin(userDTO.getLogin());
            Trip trip = TripFinder.findById(tripId);
            user.cancelRequest(trip);
            return new UserDTO(user);
        } catch (NoResultException e) {
            throw new UserNotFoundException("Usuario no encontrado");
        } catch (TripNotPendingException e) {
            throw new InvalidActionException("No se puede cancelar un viaje " +
                    "que no está pendiente");
        }
    }
}
