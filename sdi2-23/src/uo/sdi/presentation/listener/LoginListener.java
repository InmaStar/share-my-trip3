package uo.sdi.presentation.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import uo.sdi.presentation.BeanUser;

public class LoginListener implements PhaseListener {
    private static final long serialVersionUID = 1551644597845911013L;

    @Override
    public void afterPhase(PhaseEvent event) {
	FacesContext fc = event.getFacesContext();
	String view = fc.getViewRoot().getViewId();
	// Check if we are in are in a page restricted to logged users
	if (!(view.contains("login") || view.contains("signUp") 
		|| view.contains("listaViajesDisponibles") 
		|| view.contains("index"))) {
	    if (notLoggedIn()) {
		NavigationHandler nh = fc.getApplication()
			.getNavigationHandler();
		nh.handleNavigation(fc, null, "login");
	    }
	}
    }

    @Override
    public void beforePhase(PhaseEvent event) {
	// TODO Auto-generated method stub

    }

    private boolean notLoggedIn() {
	FacesContext context = FacesContext.getCurrentInstance();
	BeanUser user = context.getApplication()
		.evaluateExpressionGet(context, "#{user}", BeanUser.class);
	return user.isPublico();
    }

    @Override
    public PhaseId getPhaseId() {
	return PhaseId.RESTORE_VIEW;
    }

}
