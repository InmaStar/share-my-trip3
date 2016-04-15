package uo.sdi.model;

import uo.sdi.model.exception.TripNotPendingException;
import uo.sdi.model.types.UserStatus;
import uo.sdi.model.util.Association;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TUSERS")
public class User {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "promoter")
    private Set<Trip> promotedTrips = new HashSet<>();

    @ManyToMany(mappedBy = "applicants")
    private Set<Trip> appliedTrips = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Seat> seats = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;

    public User() {
    }

    public User(String login, String password, String name, String surname,
                String email, UserStatus status) {
        super();
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
    }


    public void requestSeat(Trip trip) {
        Association.ApplyTo.link(this, trip);
    }

    public void cancelRequest(Trip trip) throws TripNotPendingException {
        for (Seat seat : seats) {
            if (seat.getTrip() == trip) {
                throw new TripNotPendingException();
            }
        }
        Association.ApplyTo.unlink(this, trip);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Trip> _getPromotedTrips() {
        return promotedTrips;
    }

    public Set<Trip> _getAppliedTrips() {
        return appliedTrips;
    }

    public Set<Seat> _getSeats() {
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login != null ? login.equals(user.login) : user.login == null;

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User [id=" + id
                + ", login=" + login
                + ", password=" + password
                + ", name=" + name
                + ", surname=" + surname
                + ", status=" + status
                + ", email=" + email
                + "]";
    }

    public Set<Seat> getSeats() {
        return new HashSet<>(seats);
    }

    public Set<Trip> getPromotedTrips() {
        return new HashSet<>(promotedTrips);
    }

    public Set<Trip> getAppliedTrips() {
        return new HashSet<>(appliedTrips);
    }

    public Seat confirmSeat(Trip trip) {
        return new Seat(this, trip);
    }

    public Seat getSeatByTrip(Trip trip) {
        for (Seat seat: seats) {
            if (seat.getTrip().equals(trip)) {
                return seat;
            }
        }
        return null;
    }
}
