package uo.sdi.entrega1.acciones;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.entrega1.servlets.util.Navigation;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListApplicationsAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute("user");
        request.setAttribute("user", user);
        String rawTripId = request.getParameter("id");
        if (rawTripId == null) {
            rawTripId =  session.getAttribute("lastTrip").toString();
        }
        Long tripId = Long.valueOf(rawTripId);
        session.setAttribute("lastTrip", tripId);

        try {
            TripDTO trip =
                    Factories.services.createTripService().findById(tripId);
            List<UserDTO> applicants = Factories.services.createUserService()
                    .findApplicantsByTripId(tripId);
            request.setAttribute("applicants", applicants);
            request.setAttribute("trip", trip);
            Log.debug("Obtenida lista de aplicaciones para el viaje [%d]",
                    tripId);
        } catch (BusinessException e) {
            Log.error("Algo ha ocurrido obteniendo la "
                    + "lista de aplicaciones");
        }
        return Navigation.OPCION_EXITO;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}
