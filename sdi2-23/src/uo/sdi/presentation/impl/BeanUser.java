package uo.sdi.presentation.impl;

import java.io.Serializable;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UserDTO user;

    public boolean isPublico() {
	return user == null;
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

    public UserDTO getCurrentUser() {
	return user;
    }

    public void setCurrentUser(UserDTO user) {
	this.user = user;
    }

    public String cerrarSesion() {
	user = null;
	FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
	return "exito";
    }
}
