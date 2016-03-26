package uo.sdi.entrega1.acciones;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.InvalidActionException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.entrega1.servlets.util.Navigation;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelApplicationAction implements Accion {
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute("user");
        TripDTO trip = new TripDTO();
        trip.setId(Long.valueOf(request.getParameter("id")));

        try {
            Log.info("El usuario [%s] está cancelando plaza para el viaje " +
                    "[%d]", user.getLogin(), trip.getId());
            Factories.services.createUserService().cancelApplication(user, trip);
            return Navigation.OPCION_EXITO;

        } catch (InvalidActionException e) {
            Log.info("El usuario [%s] ha cancelado plaza para el viaje " +
                            "[%d], el cual no está pendiente",
                    user.getLogin(), trip.getId());
            return Navigation.OPCION_FRACASO;

        } catch (BusinessException e) {
            Log.error("Ha habido un problema con el usuario [%s] cancelando " +
                    "plaza para el viaje [%d]", user.getLogin(), trip.getId());
            return Navigation.OPCION_FRACASO;
        }

    }
}
