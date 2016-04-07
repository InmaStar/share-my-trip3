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
import uo.sdi.transport.UserDTO;
import uo.sdi.transport.UserTripRelationship;
import uo.sdi.util.bundle.BundleLoader;

@ManagedBean(name = "trips")
@SessionScoped
public class BeanTrips implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	List<TripDTO> viajes = new ArrayList<TripDTO>();
	List<TripDTO> viajesSeleccionados;
	List<TripDTO> filteredTrips;

	public BeanTrips() {
		listadoDisponibles();
		viajesSeleccionados = new ArrayList<TripDTO>();
	}

	public List<TripDTO> getListaViajes() {
		return viajes;
	}

	public List<TripDTO> getSeleccionados() {
		return viajesSeleccionados;
	}

	public boolean ningunSeleccionado() {
		return viajesSeleccionados.isEmpty();
	}

	public void seleccionar(TripDTO viaje) {
		if (viajesSeleccionados.contains(viaje)) {
			viajesSeleccionados.remove(viaje);
		} else {
			viajesSeleccionados.add(viaje);
		}
	}

	public String cancelarViaje(TripDTO viaje, Long userId) {
		try {
			TripService tripServ = Factories.services.createTripService();
			tripServ.cancel(viaje);
			Log.info("El viaje [%d] ha sido cancelado", viaje.getId());
			listadoPromotor(userId);
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] cancelando el viaje [%d]: [%s]", e
					.getClass().toString(), viaje.getId(), e.getMessage());
			return "error";
		}
	}

	public void cancelarViajes(Long userId) {
		for (TripDTO viaje : viajesSeleccionados) {
			try {
				Factories.services.createTripService().cancel(viaje);
				Log.info("El viaje [%d] ha sido cancelado", viaje.getId());
			} catch (Exception e) {
				Log.debug(
						"Ha ocurrido una [%s] cancelando el viaje [%d]: [%s]",
						e.getClass().toString(), viaje.getId(), e.getMessage());
			}
		}
		listadoPromotor(userId);
	}

	public String listadoDisponibles() {
		try {
			List<TripDTO> listaViajes = cargarViajes();
			viajes = new ArrayList<TripDTO>();
			for (TripDTO viaje : listaViajes) {
				if (viaje.isVisible()) {
					viajes.add(viaje);
				}
			}
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] listando "
					+ "los viajes disponibles: [%s]", e.getClass().toString(),
					e.getMessage());
			return "error";
		}
	}

	public String listadoRelacionados(Long userId) {
		try {
			List<TripDTO> listaViajes = cargarViajes();
			viajes = new ArrayList<TripDTO>();
			for (TripDTO viaje : listaViajes) {
				if (viaje.hasRelationship(userId)) {
					viajes.add(viaje);
				}
			}
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] listando "
					+ "los viajes relacionados: [%s]", e.getClass().toString(),
					e.getMessage());
			return "error";
		}
	}

	public String listadoPromotor(Long userId) {
		try {
			List<TripDTO> listaViajes = cargarViajes();
			viajes = new ArrayList<TripDTO>();
			for (TripDTO viaje : listaViajes) {
				if (viaje.isPromoter(userId)) {
					viajes.add(viaje);
				}
			}

			// inicializar la lista de seleccionados
			viajesSeleccionados.clear();

			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] listando "
					+ "los viajes relacionados: [%s]", e.getClass().toString(),
					e.getMessage());
			return "error";
		}
	}

	public String cancelarPlaza(TripDTO viaje, UserDTO usuario) {
		try {
			Log.info("Se está cancelando la plaza para el viaje "
					+ "[%d] al usuario [%d]", viaje.getId(), usuario.getId());
			Factories.services.createUserService().cancelApplication(usuario,
					viaje);
			listadoDisponibles();
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] cancelando la plaza "
					+ "del usuario [%d] en el viaje [%d]: [%s]", e.getClass()
					.toString(), usuario.getId(), viaje.getId(), e.getMessage());
			return "error";
		}
	}

	public String solicitarPlaza(TripDTO viaje, UserDTO usuario) {
		try {
			Log.info("Se está solicitando plaza para el viaje "
					+ "[%d] al usuario [%d]", viaje.getId(), usuario.getId());
			Factories.services.createUserService().requestSeat(usuario, viaje);
			listadoDisponibles();
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] solicitando plaza "
					+ "para el usuario [%d] en el viaje [%d]: [%s]", e
					.getClass().toString(), usuario.getId(), viaje.getId(), e
					.getMessage());
			return "error";
		}
	}

	public String localizarTripStatus(TripStatus status) {
		ResourceBundle bundle = BundleLoader.load("msgs");
		return bundle.getString(status.name());
	}

	public String localizarTripRelationship(UserTripRelationship relationship) {
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

	public List<TripDTO> getFilteredTrips() {
		return filteredTrips;
	}

	public void setFilteredTrips(List<TripDTO> filteredTrips) {
		this.filteredTrips = filteredTrips;
	}

}
