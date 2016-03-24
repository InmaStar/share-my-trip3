package uo.sdi.entrega1.acciones;

import alb.util.log.Log;
import uo.sdi.business.TripService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.TripNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.entrega1.servlets.util.Navigation;
import uo.sdi.transport.TripDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelarViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        Long idViaje = Long.parseLong(request.getParameter("id"));

        try {
            TripService tripServ = Factories.services.createTripService();
            tripServ.cancel(new TripDTO(idViaje));
            Log.info("El viaje [%d] ha sido cancelado", idViaje);
            return Navigation.OPCION_EXITO;

        } catch (TripNotFoundException e) {
            Log.error("El viaje [%d] no existe", idViaje);
            return Navigation.OPCION_FRACASO;

        } catch (BusinessException e) {
            Log.error("Ha ocurrido un problema cancelando el viaje [%d]",
                    idViaje);
            return Navigation.OPCION_FRACASO;
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}
