package uo.sdi.business.impl;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.trip.*;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class EjbTripService implements LocalTripService, RemoteTripService {
    @Override
    public TripDTO findById(Long id) throws BusinessException {
        return new FindById(id).execute();
    }

    @Override
    public List<TripDTO> findAll() throws BusinessException {
        return new FindAll().execute();
    }

    @Override
    public TripDTO update(TripDTO viaje) throws BusinessException {
        return new UpdateTrip(viaje).execute();
    }

    @Override
    public TripDTO insert(TripDTO viaje, UserDTO promotor)
            throws BusinessException {
        return new InsertTrip(viaje, promotor).execute();
    }

    @Override
    public TripDTO cancel(TripDTO viaje) throws BusinessException {
        return new CancelTrip(viaje).execute();
    }

    @Override
    public TripDTO confirmApplication(Long userId, TripDTO viaje)
	    throws BusinessException {
	return new ConfirmApplication(userId, viaje).execute();
    }

    @Override
    public TripDTO cancelSeat(Long userId, TripDTO viaje)
	    throws BusinessException {
	return new CancelSeat(userId, viaje).execute();
    }
}
