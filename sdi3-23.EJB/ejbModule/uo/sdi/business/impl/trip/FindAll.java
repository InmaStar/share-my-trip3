package uo.sdi.business.impl.trip;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.Trip;
import uo.sdi.persistence.TripFinder;
import uo.sdi.transport.TripDTO;

import java.util.ArrayList;
import java.util.List;

public class FindAll implements Command<List<TripDTO>> {
    @Override
    public List<TripDTO> execute() throws BusinessException {
        List<TripDTO> list = new ArrayList<>();
        for (Trip trip : TripFinder.findAll()) {
            list.add(new TripDTO(trip));
        }
        return list;
    }
}
