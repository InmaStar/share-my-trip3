package uo.sdi.presentation.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import alb.util.log.Log;
import uo.sdi.business.TripService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.transport.TripDTO;

@ManagedBean(name = "trips")
@SessionScoped
public class BeanTrips implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    List<TripDTO> viajes = new ArrayList<TripDTO>();

    public BeanTrips() {
	listado();
    }

    public List<TripDTO> getListaViajes() {
	return viajes;
    }
    
    public String cancelarViaje(TripDTO viaje) {
	try{
	    TripService tripServ = Factories.services.createTripService();
	    tripServ.cancel(viaje);
	    Log.info("El viaje [%d] ha sido cancelado", viaje.getId());
	    return "exito";
	} catch(Exception e){
	    Log.debug("Ha ocurrido una [%s] cancelando el viaje [%d]: [%s]", 
		    e.getClass().toString(),
		    viaje.getId(),
		    e.getMessage());
	    return "error";
	}
    }

    public String listado() {
	try {
	    viajes = cargarViajes();
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] listando viajes: [%s]", 
		    e.getClass().toString(),
		    e.getMessage());
	    return "error";
	}
    }

    private List<TripDTO> cargarViajes() throws BusinessException {
	List<TripDTO> listaViajes = Factories.services.createTripService()
		.findAll();
	Log.info("Obtenida lista de viajes conteniendo [%d] viajes",
		listaViajes.size());
	return listaViajes;
    }
}
