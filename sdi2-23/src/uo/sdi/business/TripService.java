package uo.sdi.business;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import java.util.List;

public interface TripService {
    TripDTO findById(Long id) throws BusinessException;

    List<TripDTO> findAll() throws BusinessException;

    TripDTO update(TripDTO viaje) throws BusinessException;

    TripDTO insert(TripDTO viaje, UserDTO promotor) throws BusinessException;

    TripDTO cancel(TripDTO viaje) throws BusinessException;
}
