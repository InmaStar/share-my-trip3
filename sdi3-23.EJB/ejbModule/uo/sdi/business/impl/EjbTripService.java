package uo.sdi.business.impl;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.CommandExecutor;
import uo.sdi.business.impl.trip.*;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class EjbTripService implements LocalTripService, RemoteTripService {
    @Override
    public TripDTO findById(Long id) throws BusinessException {
        return CommandExecutor.execute(new FindById(id));
    }

    @Override
    public List<TripDTO> findAll() throws BusinessException {
        return CommandExecutor.execute(new FindAll());
    }

    @Override
    public TripDTO update(TripDTO viaje) throws BusinessException {
        return CommandExecutor.execute(new UpdateTrip(viaje));
    }

    @Override
    public TripDTO insert(TripDTO viaje, UserDTO promotor)
            throws BusinessException {
        return CommandExecutor.execute(new InsertTrip(viaje, promotor));
    }

    @Override
    public TripDTO cancel(TripDTO viaje) throws BusinessException {
        return CommandExecutor.execute(new CancelTrip(viaje));
    }

    @Override
    public TripDTO confirmApplication(Long userId, TripDTO viaje)
	    throws BusinessException {
	return CommandExecutor.execute(new ConfirmApplication(userId, viaje));
    }

    @Override
    public TripDTO cancelSeat(Long userId, TripDTO viaje)
	    throws BusinessException {
	return CommandExecutor.execute(new CancelSeat(userId, viaje));
    }
}
