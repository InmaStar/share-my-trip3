package uo.sdi.transport;

import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1508956227600232980L;
    
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private UserStatus status = UserStatus.ACTIVE;

    private Set<SeatDTO> seats = new HashSet<>();
    private Set<TripDTO> promotedTrips = new HashSet<>();
    private Set<TripDTO> appliedTrips = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user, false);
    }

    public UserDTO(User user, boolean withAppliedTrips) {
        this(user, withAppliedTrips, false);
    }

    public UserDTO(User user, boolean withAppliedTrips, boolean withSeats) {
        this(user, withAppliedTrips, withSeats, false);
    }

    public UserDTO(User user, boolean withAppliedTrips, boolean withSeats,
                   boolean withPromotedTrips){
        this(user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getEmail());

        if (withSeats) {
            for (Seat seat : user.getSeats()) {
                this.seats.add(new SeatDTO(seat, this));
            }
        }
        if (withPromotedTrips) {
            for (Trip trip : user.getPromotedTrips()) {
                this.promotedTrips.add(new TripDTO(trip, this));
            }
        }
        if (withAppliedTrips) {
            for (Trip trip : user.getAppliedTrips()) {
                this.promotedTrips.add(new TripDTO(trip));
            }
        }
    }

    public UserDTO(String login) {
        this.login = login;
    }

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserDTO(String login,
                   String password,
                   String name,
                   String surname,
                   String email) {
        this(null, login, password, name, surname, email);
    }

    public UserDTO(Long id,
                   String login,
                   String password,
                   String name,
                   String surname,
                   String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        return login != null ? login.equals(userDTO.login) : userDTO.login == null;

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return login;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatDTO> seats) {
        this.seats = seats;
    }

    public Set<TripDTO> getPromotedTrips() {
        return promotedTrips;
    }

    public void setPromotedTrips(Set<TripDTO> promotedTrips) {
        this.promotedTrips = promotedTrips;
    }

    public Set<TripDTO> getAppliedTrips() {
        return appliedTrips;
    }
    
    public TripDTO findAppliedTrip(Long id){
	TripDTO result = null;
	TripDTO trip;
	 for (Iterator<TripDTO> it = appliedTrips.iterator(); it.hasNext(); ) {
	        trip = it.next();
	        if (trip.getId().equals(id)){
	            result = trip;
	            break;
	        }
	    }
	 return result;
    }
}
