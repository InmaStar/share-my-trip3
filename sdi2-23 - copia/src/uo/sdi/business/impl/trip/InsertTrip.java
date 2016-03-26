package uo.sdi.business.impl.trip;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.TripAlreadyExistsException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.types.AddressPoint;
import uo.sdi.model.types.Waypoint;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import javax.persistence.EntityExistsException;

public class InsertTrip implements Command<TripDTO> {
    private TripDTO trip;
    private Long promoterId;

    public InsertTrip(TripDTO viaje, UserDTO promotor) {
        this.trip = viaje;
        this.promoterId = promotor.getId();
    }

    @Override
    public TripDTO execute() throws BusinessException {
        try {
            User promoter = UserFinder.findById(promoterId);

            Trip newTrip = new Trip(promoter);

            newTrip.setArrivalDate(trip.getArrivalDate());
            newTrip.setAvailablePax(trip.getAvailablePax());
            newTrip.setClosingDate(trip.getClosingDate());
            newTrip.setComments(trip.getComments());
            newTrip.setDepartureDate(trip.getDepartureDate());
            newTrip.setEstimatedCost(trip.getEstimatedCost());
            newTrip.setMaxPax(trip.getMaxPax());
            newTrip.setStatus(trip.getStatus());

            AddressPoint departure = newTrip.getDeparture();
            departure.setAddress(trip.getDeparture().getAddress());
            departure.setCity(trip.getDeparture().getCity());
            departure.setCountry(trip.getDeparture().getCountry());
            departure.setState(trip.getDeparture().getState());
            departure.setZipCode(trip.getDeparture().getZipCode());
            departure.setWaypoint(
                    new Waypoint(trip.getDeparture().getLat(),
                            trip.getDeparture().getLon()));

            AddressPoint destination = newTrip.getDestination();
            destination.setAddress(trip.getDestination().getAddress());
            destination.setCity(trip.getDestination().getCity());
            destination.setCountry(trip.getDestination().getCountry());
            destination.setState(trip.getDestination().getState());
            destination.setZipCode(trip.getDestination().getZipCode());
            destination.setWaypoint(
                    new Waypoint(trip.getDestination().getLat(),
                            trip.getDestination().getLon()));

            Jpa.getManager().persist(newTrip);
            return new TripDTO(newTrip);
        } catch (EntityExistsException e) {
            throw new TripAlreadyExistsException("El viaje ya existe");
        }
    }
}
