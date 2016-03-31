package uo.sdi.presentation.impl;

import java.io.Serializable;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import alb.util.log.Log;
import uo.sdi.infrastructure.Factories;
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

    public String cancelarPlaza(TripDTO viaje) {
	try {
	    Factories.services.createUserService().cancelSeat(user.getId(),
		    viaje);
	    Log.info("Se está cancelando la plaza para el viaje "
		    + "[%d] al usuario [%d]", viaje.getId(), user.getId());
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] cancelando la plaza "
		    + "del usuario [%d] en el viaje [%d]: [%s]", e.getClass()
		    .toString(), user.getId(), viaje.getId(), e.getMessage());
	    return "error";
	}
    }

    public String solicitarPlaza(TripDTO viaje) {
	try {
	    Log.info("Se está solicitando plaza para el viaje "
		    + "[%d] al usuario [%d]", viaje.getId(), user);
	    Factories.services.createUserService().requestSeat(user, viaje);
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] solicitando plaza "
		    + "para el usuario [%d] en el viaje [%d]: [%s]", e
		    .getClass().toString(), user.getId(), viaje.getId(), e
		    .getMessage());
	    return "error";
	}
    }
}
