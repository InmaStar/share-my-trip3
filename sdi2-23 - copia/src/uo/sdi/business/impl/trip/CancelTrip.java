package uo.sdi.business.impl.trip;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.TripNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.Trip;
import uo.sdi.model.types.TripStatus;
import uo.sdi.persistence.TripFinder;
import uo.sdi.transport.TripDTO;

import javax.persistence.NoResultException;

public class CancelTrip implements Command<TripDTO> {
    private TripDTO trip;

    public CancelTrip(TripDTO viaje) {
        this.trip = viaje;
    }

    @Override
    public TripDTO execute() throws BusinessException {
        try {
            Trip updatedTrip = TripFinder.findById(trip.getId());
            updatedTrip.setStatus(TripStatus.CANCELLED);
            return new TripDTO(updatedTrip);
        } catch (NoResultException e) {
            throw new TripNotFoundException("El viaje no existe");
        }
    }
}
