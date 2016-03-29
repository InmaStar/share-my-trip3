package uo.sdi.presentation;

import java.util.Date;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.entrega1.acciones.RegistrarViajeAction;
import uo.sdi.infrastructure.Factories;
import uo.sdi.transport.AddressPointDTO;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

@ManagedBean(name = "trip")
@SessionScoped
public class BeanTrip {
    
    private TripDTO viaje;
    
    public void setViaje(TripDTO viaje){
	this.viaje = viaje;
    }
    
    public Long getId(){
	return viaje.getId();
    }
    
    public AddressPointDTO getDeparture() {
        return viaje.getDeparture();
    }

    public AddressPointDTO getDestination() {
        return viaje.getDestination();
    }
   
    public Date getArrivalDate() {
        return viaje.getArrivalDate();
    }
    
    public void setArrivalDate(Date arrivalDate) {
        viaje.setArrivalDate(arrivalDate);
    }
    
    public Date getDepartureDate() {
        return viaje.getDepartureDate();
    }
    
    public void setDepartureDate(Date departureDate) {
       viaje.setDepartureDate(departureDate);
    }
    
    public Date getClosingDate() {
        return viaje.getClosingDate();
    }
    
    public void setClosingDate(Date closingDate) {
        viaje.setClosingDate(closingDate);;
    }
    
    public Integer getAvailablePax() {
        return viaje.getAvailablePax();
    }
    
    public void setAvailablePax(Integer availablePax) {
        viaje.setAvailablePax(availablePax);
    }
    
    public Integer getMaxPax() {
        return viaje.getMaxPax();
    }
    
    public void setMaxPax(Integer maxPax) {
       viaje.setMaxPax(maxPax);
    }
    
    public Double getEstimatedCost() {
        return viaje.getEstimatedCost();
    }
    
    public void setEstimatedCost(Double estimatedCost) {
        viaje.setEstimatedCost(estimatedCost);
    }
    
    public String getComments() {
        return viaje.getComments();
    }
    
    public void setComments(String comments) {
        viaje.setComments(comments);
    }
    
    public Set<UserDTO> getApplicants() {
        return viaje.getApplicants();
    }
    
    public String modificar(){
	try{
	    Factories.services.createTripService().update(viaje);
	    Log.info("Se ha modificado el viaje [%d] con éxito", viaje.getId());
	    return "exito";
	} catch(Exception e){
	    Log.debug("Ha ocurrido una [%s] modificando el viaje [%d]: [%s]", 
		    e.getClass().toString(),
		    viaje.getId(),
		    e.getMessage());
	    return "error";
	}
    }
    
    public String registrar(UserDTO promotor){
	try{
	    Factories.services.createTripService().insert(viaje, promotor);
	    Log.info("El promotor [%s] ha registrado "
                    + "un nuevo viaje [%d] con éxito", promotor.getLogin(),
                    viaje.getId());
	    return "exito";
	} catch(Exception e){
	    Log.debug("Ha ocurrido una [%s] registrando el viaje [%d]: [%s]", 
		    e.getClass().toString(),
		    viaje.getId(),
		    e.getMessage());
	    return "error";
	}
    }
    
    public boolean isVisible(){
	return viaje.isVisible();
    }
    
    public boolean isExcluded(Long userId){
	return viaje.isExcluded(userId);
    }
    
    public boolean isPending(Long userId){
	return viaje.isPending(userId);
    }
    
    public String confirmarPasajero(Long userId){
	return null; //TODO
    }
    
    public String excluirPasajero(Long userId){
	return null; //TODO
    }

}
