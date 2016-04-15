package uo.sdi.model;

import uo.sdi.model.types.SeatKey;
import uo.sdi.model.types.TravelStatus;
import uo.sdi.model.util.Association;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TSEATS")
@IdClass(SeatKey.class)
public class Seat {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Trip trip;

    private String comment;
    @Enumerated(EnumType.ORDINAL)
    private TravelStatus status = TravelStatus.ACCEPTED;

    @OneToMany(mappedBy = "from")
    private Set<Rating> from = new HashSet<>();

    @OneToMany(mappedBy = "about")
    private Set<Rating> about = new HashSet<>();

    public Seat() {
    }

    public Seat(User user, Trip trip) {
        Association.SitIn.link(this, user, trip);
    }

    public User getUser() {
        return user;
    }

    public Trip getTrip() {
        return trip;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }

    public void _setUser(User user) {
        this.user = user;
    }

    public void _setTrip(Trip trip) {
        this.trip = trip;
    }

    public Set<Rating> _getFrom() {
        return from;
    }

    public Set<Rating> _getAbout() {
        return about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (!user.equals(seat.user)) return false;
        return trip.equals(seat.trip);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + trip.hashCode();
        return result;
    }
}
