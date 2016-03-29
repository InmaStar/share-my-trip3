package uo.sdi.presentation;

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
public class BeanTrips {
    List<TripDTO> viajes;

    public BeanTrips() throws BusinessException {
	viajes = cargarViajes();
    }

    public List<TripDTO> getListaViajes() {
	return viajes;
    }
    
    public String cancelarViaje(Long idViaje) {
	try{
	    TripService tripServ = Factories.services.createTripService();
	    tripServ.cancel(new TripDTO(idViaje));
	    Log.info("El viaje [%d] ha sido cancelado", idViaje);
	    return "exito";
	} catch(BusinessException e){
	    Log.debug("Ha ocurrido una excepción: [%s]", e.getMessage());
	    return "error";
	}
    }

    public String listado() {
	try {
	    viajes = cargarViajes();
	    return "exito";
	} catch (BusinessException e) {
	    Log.debug("Ha ocurrido una excepción: [%s]", e.getMessage());
	    return "error";
	}
    }

    private List<TripDTO> cargarViajes() throws BusinessException {
	List<TripDTO> listaViajes = Factories.services.createTripService()
		.findAll();
	Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
		viajes.size());
	return listaViajes;
    }
}
