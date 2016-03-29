package uo.sdi.presentation;

import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser {

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
    
    public String cerrarSesion(){
	 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	 return "exito";
    }
    
    public String cancelarPlaza(TripDTO viaje){
	try{
            Factories.services.createUserService()
                    .cancelSeat(user.getId(), viaje);
            Log.info("Se está cancelando la plaza para el viaje " +
                    "[%d] al usuario [%d]", viaje.getId(), user.getId());
            return "exito";
	} catch (BusinessException e){
	    Log.debug("Ha ocurrido una excepción: [%s]", e.getMessage());
	    return "error";
	}
    }
    
    public String solicitarPlaza(TripDTO viaje){
	try{
	    Log.info("Se está dando plaza para el viaje " +
                    "[%d] al usuario [%d]", viaje.getId(), user.getId());
            Factories.services.createUserService()
                    .confirmApplication(user.getId(), viaje);
            return "exito";
	} catch (BusinessException e){
	    Log.debug("Ha ocurrido una excepción: [%s]", e.getMessage());
	    return "error";
	}
    }
   
}
