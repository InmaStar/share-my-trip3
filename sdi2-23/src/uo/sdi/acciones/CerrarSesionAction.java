package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.servlets.util.Navigation;
import uo.sdi.transport.UserDTO;

public class CerrarSesionAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session=request.getSession();
		Log.info("El usuario [%s] ha salido de sesión",
				((UserDTO)session.getAttribute("user"))
					.getLogin());
		session.invalidate();
		return Navigation.OPCION_EXITO;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
}
