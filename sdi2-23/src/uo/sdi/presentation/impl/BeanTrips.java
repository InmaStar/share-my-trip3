package uo.sdi.presentation.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import alb.util.log.Log;
import uo.sdi.business.TripService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.types.TripStatus;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserTripRelationship;
import uo.sdi.util.bundle.BundleLoader;

@ManagedBean(name = "trips")
@SessionScoped
public class BeanTrips implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    List<TripDTO> viajes = new ArrayList<TripDTO>();

    public BeanTrips() {
	listadoDisponibles();
    }

    public List<TripDTO> getListaViajes() {
	return viajes;
    }
    
    public void cancelarViaje(TripDTO viaje, Long userId) {
	try{
	    TripService tripServ = Factories.services.createTripService();
	    tripServ.cancel(viaje);
	    Log.info("El viaje [%d] ha sido cancelado", viaje.getId());
	    listadoPromotor(userId);
	} catch(Exception e){
	    Log.debug("Ha ocurrido una [%s] cancelando el viaje [%d]: [%s]", 
		    e.getClass().toString(),
		    viaje.getId(),
		    e.getMessage());
	}
    }

    public String listadoDisponibles() {
	try {
	    List<TripDTO> listaViajes = cargarViajes();
	    viajes = new ArrayList<TripDTO>();
	    for(TripDTO viaje : listaViajes){
		if(viaje.isVisible()){
		    viajes.add(viaje);
		}
	    }
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] listando "
	    	+ "los viajes disponibles: [%s]", 
		    e.getClass().toString(),
		    e.getMessage());
	    return "error";
	}
    }
    
    public String listadoRelacionados(Long userId) {
	try {
	    List<TripDTO> listaViajes = cargarViajes();
	    viajes = new ArrayList<TripDTO>();
	    for(TripDTO viaje : listaViajes){
		if(viaje.hasRelationship(userId)){
		    viajes.add(viaje);
		}
	    }
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] listando "
	    	+ "los viajes relacionados: [%s]", 
		    e.getClass().toString(),
		    e.getMessage());
	    return "error";
	}
    }

    public String listadoPromotor(Long userId) {
	try {
	    List<TripDTO> listaViajes = cargarViajes();
	    viajes = new ArrayList<TripDTO>();
	    for(TripDTO viaje : listaViajes){
		if(viaje.isPromoter(userId)){
		    viajes.add(viaje);
		}
	    }
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] listando "
	    	+ "los viajes relacionados: [%s]", 
		    e.getClass().toString(),
		    e.getMessage());
	    return "error";
	}
    }
    
    public String localizarTripStatus(TripStatus status){
	ResourceBundle bundle = BundleLoader.load("msgs");
	return bundle.getString(status.name());
    }
    
    public String localizarTripRelationship(UserTripRelationship relationship){
   	ResourceBundle bundle = BundleLoader.load("msgs");
   	return bundle.getString(relationship.name());
       }

    private List<TripDTO> cargarViajes() throws BusinessException {
	List<TripDTO> listaViajes = Factories.services.createTripService()
		.findAll();
	Log.info("Obtenida lista de viajes conteniendo [%d] viajes",
		listaViajes.size());
	return listaViajes;
    }
    
}
