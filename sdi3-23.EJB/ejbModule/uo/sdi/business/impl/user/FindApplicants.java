package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.TripNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.TripFinder;
import uo.sdi.transport.UserDTO;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class FindApplicants implements Command<List<UserDTO>> {
    private Long tripId;

    public FindApplicants(Long tripId) {
        this.tripId = tripId;
    }

    @Override
    public List<UserDTO> execute() throws BusinessException {
        try {
            Trip trip = TripFinder.findById(tripId);
            List<UserDTO> list = new ArrayList<>();
            for (User user : trip.getApplicants()) {
                list.add(new UserDTO(user, false));
            }
            return list;
        } catch (NoResultException e) {
            throw new TripNotFoundException("El viaje no existe");
        }
    }
}
