package uo.sdi.presentation.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import alb.util.log.Log;
import uo.sdi.infrastructure.BusinessFactories;
import uo.sdi.model.types.AddressPoint;
import uo.sdi.model.types.TripStatus;
import uo.sdi.transport.AddressPointDTO;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;
import uo.sdi.transport.UserTripRelationship;
import uo.sdi.util.bundle.BundleLoader;
import uo.sdi.util.random.RandomTripGenerator;

@ManagedBean(name = "trip")
@SessionScoped
public class BeanTrip implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private TripDTO viaje;
	private boolean generado;
	private Date today;

	public void initViaje() {
		this.viaje = new TripDTO();
		viaje.setDeparture(new AddressPointDTO());
		viaje.setDestination(new AddressPointDTO());
		setMaxPax(1);
		setEstimatedCost(0.5);
		today = new Date();
		generado = false;
	}

	public void generarViaje() {
		if (generado) {
			this.viaje = RandomTripGenerator.generateTrip();
		} else {
			initViaje();
		}
	}

	public Date getToday() {
		return today;
	}

	public void setViaje(TripDTO viaje) {
		this.viaje = viaje;
	}

	public Long getId() {
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
		viaje.setClosingDate(closingDate);
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

	public String modificar() {
		try {
			viaje = BusinessFactories.services.createTripService().update(viaje);
			Log.info("Se ha modificado el viaje [%d] con éxito", viaje.getId());
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] modificando el viaje [%d]: [%s]", e
					.getClass().toString(), viaje.getId(), e.getMessage());
			return "error";
		}
	}

	public String registrar(UserDTO promotor) {
		try {
			viaje.setStatus(TripStatus.OPEN);
			viaje = BusinessFactories.services.createTripService().insert(viaje,
					promotor);
			Log.info("El promotor [%s] ha registrado "
					+ "un nuevo viaje [%d] con éxito", promotor.getLogin(),
					viaje.getId());
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] registrando el viaje [%d]: [%s]", e
					.getClass().toString(), viaje.getId(), e.getMessage());
			return "error";
		}
	}

	public String confirmarPasajero(Long userId) {
		try {
			Log.info("Se está dando plaza para el viaje "
					+ "[%d] al usuario [%d]", viaje.getId(), userId);
			viaje = BusinessFactories.services.createTripService().confirmApplication(
					userId, viaje);
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] confirmando al usuario [%d] "
					+ "en el viaje [%d]: [%s]", e.getClass().toString(),
					userId, viaje.getId(), e.getMessage());
			return "error";
		}
	}

	public String excluirPasajero(UserDTO user) {
		try {
			Log.info("El usuario [%d] está cancelando plaza para el viaje "
					+ "[%d]", user.getId(), viaje.getId());
			viaje = BusinessFactories.services.createTripService().cancelSeat(
					user.getId(), viaje);
			return "exito";
		} catch (Exception e) {
			Log.debug("Ha ocurrido una [%s] excluyendo al usuario [%d] "
					+ "del viaje [%d]: [%s]", e.getClass().toString(),
					user.getId(), viaje.getId(), e.getMessage());
			return "error";
		}
	}

	public double getMinLat() {
		return AddressPoint.MIN_LAT;
	}

	public double getMaxLat() {
		return AddressPoint.MAX_LAT;
	}

	public double getMinLon() {
		return AddressPoint.MIN_LON;
	}

	public double getMaxLon() {
		return AddressPoint.MAX_LON;
	}

	public boolean isVisible() {
		return viaje.isVisible();
	}

	public boolean isExcluded(Long userId) {
		return viaje.isExcluded(userId);
	}

	public boolean isPending(Long userId) {
		return viaje.isPending(userId);
	}

	public boolean isAccepted(Long userId) {
		return viaje.isAccepted(userId);
	}

	public String localizarTripRelationship(UserTripRelationship relationship) {
		ResourceBundle bundle = BundleLoader.load("msgs");
		return bundle.getString(relationship.name());
	}

	public UserTripRelationship getRelationship(Long userId) {
		return viaje.getRelationship(userId);
	}

	public boolean isGenerado() {
		return generado;
	}

	public void setGenerado(boolean generado) {
		this.generado = generado;
	}

}
