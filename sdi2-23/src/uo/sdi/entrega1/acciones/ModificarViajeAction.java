package uo.sdi.entrega1.acciones;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.entrega1.servlets.util.Navigation;
import uo.sdi.transport.TripDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public class ModificarViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        TripDTO viaje;
        Long idViaje = Long.parseLong(request.getParameter("id"));
        try {
            viaje = RegistrarViajeAction.leerFormulario(request);
            viaje.setId(idViaje);
            Factories.services.createTripService().update(viaje);
            Log.info("Se ha modificado "
                    + "el viaje [%d] con éxito", idViaje);
            return Navigation.OPCION_EXITO;

        } catch (BusinessException e) {
            Log.error("Ha habido un problema modificando el viaje");
            return Navigation.OPCION_FRACASO;

        } catch (NumberFormatException e) {
            Log.info("El formato de algún campo no es válido");
            request.setAttribute("mensajeDeError",
                    "Alguno de los campos tiene un formato inválido");
            return Navigation.OPCION_FRACASO;

        } catch (ParseException e) {
            Log.info("El formato de la fecha o la hora no es válido");
            request.setAttribute("mensajeDeError",
                    "La fecha o la hora tiene un formato inválido");
            return Navigation.OPCION_FRACASO;
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}
