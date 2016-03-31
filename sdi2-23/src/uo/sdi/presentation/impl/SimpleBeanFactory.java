package uo.sdi.presentation.impl;

import java.util.Map;

import javax.faces.context.FacesContext;

import alb.util.log.Log;
import uo.sdi.presentation.BeanFactory;
import uo.sdi.presentation.BeanUser;

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

}
