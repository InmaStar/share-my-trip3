package uo.sdi.presentation;

import uo.sdi.presentation.impl.BeanTrips;
import uo.sdi.presentation.impl.BeanUser;

public interface BeanFactory {
	BeanUser createBeanUser();

	BeanTrips createBeanTrips();
}
