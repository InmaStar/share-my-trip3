package uo.sdi.acciones;

import alb.util.log.Log;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.servlets.util.Navigation;
import uo.sdi.transport.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrarseAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	HttpSession session = request.getSession();

	UserDTO newUser = new UserDTO(request.getParameter("nombreUsuario"),
		request.getParameter("password1"),
		request.getParameter("nombre"),
		request.getParameter("apellidos"),
		request.getParameter("email"));

	if (checkEmptyFields(newUser)) {
	    Log.info("El usuario no ha proporcionado alguno de sus datos");
	    request.setAttribute("mensajeDeError",
		    "Alguno de los campos está vacío");
	    return Navigation.OPCION_FRACASO;
	}

	if (!newUser.getPassword().equals(request.getParameter("password2"))) {
	    Log.info("Las contraseñas no coinciden");
	    request.setAttribute("mensajeDeError",
		    "Las contraseñas no coinciden");
	    return Navigation.OPCION_FRACASO;
	}

	if (session.getAttribute("user") != null) {
	    Log.info("Ha habido un intento de registrarse "
		    + "como nuevo usuario teniendo la sesión iniciada");
	    session.invalidate();
	    return Navigation.OPCION_FRACASO;
	}

	UserService userService = Factories.services.createUserService();
	try {
	    userService.save(newUser);
	    session.setAttribute("user", newUser);
	    Log.info("El usuario [%s] se ha registrado con éxito", newUser);
	    return Navigation.OPCION_EXITO;

	} catch (UserAlreadyExistsException e) {
	    Log.info("Ya existe un usuario con el nombre [%s]",
		    newUser.getLogin());
	    request.setAttribute("mensajeDeError", "Ya existe un usuario con"
		    + " el nombre " + newUser.getLogin());
	    return Navigation.OPCION_FRACASO;

	} catch (BusinessException e) {
	    Log.info("Ha habido un problema al registrar al usuario [%s]",
		    newUser.getLogin());
	    return Navigation.OPCION_FRACASO;
	}
    }

    private boolean checkEmptyFields(UserDTO user) {
	return user.getLogin().isEmpty() || user.getPassword().isEmpty()
		|| user.getName().isEmpty() || user.getSurname().isEmpty()
		|| user.getEmail().isEmpty();
    }

    @Override
    public String toString() {
	return getClass().getName();
    }
}
