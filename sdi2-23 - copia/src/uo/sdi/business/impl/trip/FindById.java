package uo.sdi.business.impl.trip;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.TripNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.persistence.TripFinder;
import uo.sdi.transport.TripDTO;

import javax.persistence.NoResultException;

public class FindById implements Command<TripDTO> {
    private Long id;

    public FindById(Long id) {
        this.id = id;
    }


    @Override
    public TripDTO execute() throws BusinessException {
        try {
            return new TripDTO(TripFinder.findById(id));
        } catch (NoResultException e) {
            throw new TripNotFoundException("Viaje no encontrado");
        }
    }
}
