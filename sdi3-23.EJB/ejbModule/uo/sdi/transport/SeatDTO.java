package uo.sdi.transport;

import uo.sdi.model.Seat;
import uo.sdi.model.types.TravelStatus;

public class SeatDTO {
    private UserDTO user;
    private TripDTO trip;
    private TravelStatus status;
    private String comment;

    public SeatDTO() {
    }

    public SeatDTO(Seat seat, UserDTO user) {
        this(seat, user, new TripDTO(seat.getTrip()));
    }

    public SeatDTO(Seat seat, TripDTO trip) {
        this(seat, new UserDTO(seat.getUser()), trip);
    }

    public SeatDTO(Seat seat, UserDTO user, TripDTO trip) {
        this.user = user;
        this.trip = trip;
        this.status = seat.getStatus();
        this.comment = seat.getComment();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
