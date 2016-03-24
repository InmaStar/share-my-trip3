package uo.sdi.entrega1.acciones;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.entrega1.servlets.util.Navigation;
import uo.sdi.transport.TripDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelSeatAction implements Accion {
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        TripDTO trip = new TripDTO();
        trip.setId(Long.valueOf(request.getParameter("tripId")));
        Long userId = Long.valueOf(request.getParameter("userId"));

        try {
            Log.info("Se está cancelando la plaza para el viaje " +
                    "[%d] al usuario [%d]", trip.getId(), userId);
            Factories.services.createUserService()
                    .cancelSeat(userId, trip);
            return Navigation.OPCION_EXITO;

        }  catch (BusinessException e) {
            Log.error("Ha habido un problema cancelando plaza al usuario [%d]" +
                    " para el viaje [%d]", userId, trip.getId());
            return Navigation.OPCION_FRACASO;
        }

    }
}

