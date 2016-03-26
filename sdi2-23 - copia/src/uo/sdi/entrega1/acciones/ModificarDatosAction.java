package uo.sdi.entrega1.acciones;

import alb.util.log.Log;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.entrega1.servlets.util.Navigation;
import uo.sdi.transport.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificarDatosAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        HttpSession session = request.getSession();

        UserDTO user = ((UserDTO) session.getAttribute("user"));
        user.setEmail(request.getParameter("email"));
        user.setName(request.getParameter("nombre"));
        user.setSurname(request.getParameter("apellidos"));

        String password = request.getParameter("password1");

        if (!password.isEmpty()){
            if(request.getParameter("password2").equals(password)) {
        	user.setPassword(password);
            }
            else {
        	Log.info("Las contraseñas no coinciden");
        	request.setAttribute("mensajeDeError", 
        		"Las contraseñas no coinciden");
        	return Navigation.OPCION_FRACASO;
            }
        } 

        try {
            UserService userService = Factories.services.createUserService();
            user = userService.update(user);
            Log.debug("Modificado los datos del usuario [%s]", user);

        } catch (UserNotFoundException e) {
            Log.error("El usuario [%s] no existe", user);
            return Navigation.OPCION_FRACASO;

        } catch (BusinessException e) {
            Log.error("Algo ha ocurrido actualizando los datos de [%s]", user);
        }

        return Navigation.OPCION_EXITO;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}
