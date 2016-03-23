package uo.sdi.acciones;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.servlets.util.Navigation;
import uo.sdi.transport.TripDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccederModificarViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        String idViaje = request.getParameter("id");
        TripDTO trip = null;
        try {
            trip = Factories.services.createTripService()
                    .findById(Long.valueOf(idViaje));
        } catch (BusinessException e) {
            Log.info("El viaje [%d] no existe", idViaje);
            return Navigation.OPCION_FRACASO;
        }
        request.getSession().setAttribute("viaje", trip);
        return Navigation.OPCION_EXITO;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
