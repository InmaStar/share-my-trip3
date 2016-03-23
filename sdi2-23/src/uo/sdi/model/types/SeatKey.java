package uo.sdi.model.types;

import java.io.Serializable;

public class SeatKey implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6918170647894095100L;
    private Long user;
    private Long trip;

    public SeatKey() {

    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getTrip() {
        return trip;
    }

    public void setTrip(Long trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatKey seatKey = (SeatKey) o;

        if (!user.equals(seatKey.user)) return false;
        return trip.equals(seatKey.trip);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + trip.hashCode();
        return result;
    }
}
