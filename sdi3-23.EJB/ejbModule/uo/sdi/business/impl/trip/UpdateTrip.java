package uo.sdi.business.impl.trip;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.TripNotFoundException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.Trip;
import uo.sdi.model.types.AddressPoint;
import uo.sdi.model.types.Waypoint;
import uo.sdi.persistence.TripFinder;
import uo.sdi.transport.TripDTO;

import javax.persistence.NoResultException;

public class UpdateTrip implements Command<TripDTO> {
    private TripDTO trip;

    public UpdateTrip(TripDTO viaje) {
        this.trip = viaje;
    }

    @Override
    public TripDTO execute() throws BusinessException {
        try {
            Trip updatedTrip = TripFinder.findById(trip.getId());
            updatedTrip.setArrivalDate(trip.getArrivalDate());
            updatedTrip.setAvailablePax(trip.getAvailablePax());
            updatedTrip.setClosingDate(trip.getClosingDate());
            updatedTrip.setComments(trip.getComments());
            updatedTrip.setDepartureDate(trip.getDepartureDate());
            updatedTrip.setEstimatedCost(trip.getEstimatedCost());
            updatedTrip.setMaxPax(trip.getMaxPax());
            updatedTrip.setStatus(trip.getStatus());

            AddressPoint departure = updatedTrip.getDeparture();
            departure.setAddress(trip.getDeparture().getAddress());
            departure.setCity(trip.getDeparture().getCity());
            departure.setCountry(trip.getDeparture().getCountry());
            departure.setState(trip.getDeparture().getState());
            departure.setZipCode(trip.getDeparture().getZipCode());
            departure.setWaypoint(
                    new Waypoint(trip.getDeparture().getLat(),
                            trip.getDeparture().getLon()));

            AddressPoint destination = updatedTrip.getDestination();
            destination.setAddress(trip.getDestination().getAddress());
            destination.setCity(trip.getDestination().getCity());
            destination.setCountry(trip.getDestination().getCountry());
            destination.setState(trip.getDestination().getState());
            destination.setZipCode(trip.getDestination().getZipCode());
            destination.setWaypoint(
                    new Waypoint(trip.getDestination().getLat(),
                            trip.getDestination().getLon()));
            return trip;
        } catch (NoResultException e) {
            throw new TripNotFoundException("El viaje no existe");
        }
    }
}
