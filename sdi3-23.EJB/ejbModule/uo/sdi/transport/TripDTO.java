package uo.sdi.transport;

import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.types.TravelStatus;
import uo.sdi.model.types.TripStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TripDTO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 5941148050847162374L;
    
    private Long id;
    private AddressPointDTO departure;
    private AddressPointDTO destination;
    private Date arrivalDate;
    private Date departureDate;
    private Date closingDate;
    private Integer availablePax = 0;
    private Integer maxPax = 0;
    private Double estimatedCost = 0.0;
    private String comments;
    private TripStatus status;

    private UserDTO promoter;
    private Set<UserDTO> applicants = new HashSet<>();
    private Set<SeatDTO> seats = new HashSet<>();

    public TripDTO() {
    }

    public TripDTO(Trip trip) {
        this(trip, new UserDTO(trip.getPromoter()));
    }

    public TripDTO(Trip trip, UserDTO promoter) {
        this.id = trip.getId();
        this.departure = new AddressPointDTO(trip.getDeparture());
        this.destination = new AddressPointDTO(trip.getDestination());
        this.arrivalDate = trip.getArrivalDate();
        this.departureDate = trip.getDepartureDate();
        this.closingDate = trip.getClosingDate();
        this.availablePax = trip.getAvailablePax();
        this.maxPax = trip.getMaxPax();
        this.estimatedCost = trip.getEstimatedCost();
        this.comments = trip.getComments();
        this.status = trip.getStatus();
        this.promoter = promoter;

        for (User user : trip.getApplicants()) {
            this.applicants.add(new UserDTO(user, false));
        }
        for (Seat seat : trip.getSeats()) {
            this.seats.add(new SeatDTO(seat, this));
        }
    }

    public TripDTO(Long idViaje) {
        this.id = idViaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressPointDTO getDeparture() {
        return departure;
    }

    public void setDeparture(AddressPointDTO departure) {
        this.departure = departure;
    }

    public AddressPointDTO getDestination() {
        return destination;
    }

    public void setDestination(AddressPointDTO destination) {
        this.destination = destination;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Integer getAvailablePax() {
        return availablePax;
    }

    public void setAvailablePax(Integer availablePax) {
        this.availablePax = availablePax;
    }

    public Integer getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(Integer maxPax) {
        this.maxPax = maxPax;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public UserDTO getPromoter() {
        return promoter;
    }

    public void setPromoter(UserDTO promoter) {
        this.promoter = promoter;
    }

    public Set<UserDTO> getApplicants() {
        return applicants;
    }

    public void setApplicants(Set<UserDTO> applicants) {
        this.applicants = applicants;
    }

    public Set<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatDTO> seats) {
        this.seats = seats;
    }

    public boolean isOpen() {
        return this.status == TripStatus.OPEN;
    }

    public boolean isVisible() {
        return !isOutdated() && isOpen() && !isFull();
    }

    public boolean isOutdated() {
        return closingDate.before(new Date());
    }

    public boolean isFull() {
        return availablePax == 0;
    }

    public boolean isPromoter(Long userId) {
	UserTripRelationship relation = getRelationship(userId);
        return relation!=null ? relation.equals(UserTripRelationship.PROMOTER) 
        	: false;
    }

    public boolean isPending(Long userId) {
	UserTripRelationship relation = getRelationship(userId);
        return relation!=null ? relation.equals(UserTripRelationship.PENDING)
        	: false;
    }

    public boolean isAccepted(Long userId) {
	UserTripRelationship relation = getRelationship(userId);
        return relation!=null ? relation.equals(UserTripRelationship.ACCEPTED)
        	: false;
    }

    public boolean isExcluded(Long userId) {
	UserTripRelationship relation = getRelationship(userId);
        return relation!=null ? relation.equals(UserTripRelationship.EXCLUDED)
        	: false;
    }

    public boolean hasRelationship(Long userId) {
        return getRelationship(userId)!=null;
    }

    public UserTripRelationship getRelationship(Long userId) {
        if (promoter.getId().equals(userId)) {
            return UserTripRelationship.PROMOTER;
        }
        for (SeatDTO seat : seats) {
            if (seat.getUser().getId().equals(userId)) {
                if (seat.getStatus() == TravelStatus.ACCEPTED) {
                    return UserTripRelationship.ACCEPTED;
                } else if (seat.getStatus() == TravelStatus.EXCLUDED){
                    return UserTripRelationship.EXCLUDED;
                } else {
                    return UserTripRelationship.CANCELLED;
                }
            }
        }

        for (UserDTO user : applicants) {
            if (user.getId().equals(userId)) {
                return isFull() ? UserTripRelationship.NO_SEAT 
                	: UserTripRelationship.PENDING;
            }
        }

        return null;
    }
    
    public boolean canRequestSeat(Long userId){
	return !isPromoter(userId) && !isPending(userId);
    }
    
    public boolean canCancelSeat(Long userId){
	return !isPromoter(userId) && isPending(userId);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TripDTO other = (TripDTO) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
}
