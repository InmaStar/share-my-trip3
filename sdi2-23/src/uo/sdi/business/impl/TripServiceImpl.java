package uo.sdi.business.impl;

import uo.sdi.business.TripService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.trip.*;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import java.util.List;

public class TripServiceImpl implements TripService {
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
}
