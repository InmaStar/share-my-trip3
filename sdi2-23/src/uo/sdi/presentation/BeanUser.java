package uo.sdi.presentation;

import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser {

    @ManagedProperty(value = "#{trip}")
    private BeanTrip viaje;
    private UserDTO user;
    
    public boolean isPublico(){
	return user==null;
    }
    
    public Long getId() {
	return user.getId();
    }

    public String getLogin() {
        return user.getLogin();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getName() {
        return user.getName();
    }

    public String getSurname() {
        return user.getSurname();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Set<TripDTO> getPromotedTrips() {
        return user.getPromotedTrips();
    }

    public void setCurrentUser(UserDTO user){
	this.user = user;
    }
    
    public String closeSession(){
	return null; //TODO
    }
}
