package uo.sdi.entrega1.acciones;

import alb.util.log.Log;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.entrega1.servlets.util.Navigation;
import uo.sdi.transport.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidarseAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	HttpSession session = request.getSession();

	UserDTO user = new UserDTO();
	user.setLogin(request.getParameter("nombreUsuario"));
	user.setPassword(request.getParameter("password"));

	if (user.getLogin().isEmpty()) {
	    Log.info("El usuario no ha proporcionado su nombre");
	    return Navigation.OPCION_FRACASO;
	}
	if (user.getPassword().isEmpty()) {
	    Log.info("El usuario no ha proporcionado su contraseña");
	    return Navigation.OPCION_FRACASO;
	}

	UserDTO loggedUser = (UserDTO) session.getAttribute("user");
	if (loggedUser != null) {
	    if (!loggedUser.equals(user)) {
		Log.info("Se ha intentado iniciar sesión como [%s] "
			+ "teniendo la sesión iniciada como [%s]", user,
			session.getAttribute("user"));
		session.invalidate();
		return Navigation.OPCION_FRACASO;
	    }
	}

	UserService userService = Factories.services.createUserService();
	try {
	    loggedUser = userService.findByLogin(user);
	    if (!loggedUser.getPassword().equals(user.getPassword())) {
		Log.info(
			"El usuario ha introducido una contraseña errónea para"
				+ "el login [%s]", user);
		request.setAttribute("mensajeDeError",
			"La contraseña es incorrecta");
		return Navigation.OPCION_FRACASO;

	    } else {
		session.setAttribute("user", loggedUser);
		Log.info("El usuario [%s] ha iniciado sesión", loggedUser);
		return Navigation.OPCION_EXITO;
	    }

	} catch (BusinessException e) {
	    Log.info("El usuario [%s] no está registrado", user);
	    request.setAttribute("mensajeDeError",
		    "El usuario " + user.getLogin() + " no está registrado");
	    session.invalidate();
	    return Navigation.OPCION_FRACASO;
	}

    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
