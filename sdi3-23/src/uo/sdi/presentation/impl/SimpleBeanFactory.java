package uo.sdi.presentation.impl;

import java.util.Map;

import javax.faces.context.FacesContext;

import uo.sdi.presentation.BeanFactory;
import alb.util.log.Log;

public class SimpleBeanFactory implements BeanFactory {

	@Override
	public BeanUser createBeanUser() {
		BeanUser user;
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();

		user = (BeanUser) session.get("user");
		if (user == null) {
			user = new BeanUser();
			session.put("user", user);
			Log.info("BeanUser creado de forma explícita");
		}
		return user;
	}

	@Override
	public BeanTrips createBeanTrips() {
		BeanTrips trips;
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();

		trips = (BeanTrips) session.get("trips");
		if (trips == null) {
			trips = new BeanTrips();
			session.put("trips", trips);
			Log.info("BeanTrips creado de forma explícita");
		}
		return trips;
	}

}
