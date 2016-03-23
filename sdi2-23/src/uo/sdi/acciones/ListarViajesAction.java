package uo.sdi.acciones;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.servlets.util.Navigation;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListarViajesAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user);
        }


        try {
            List<TripDTO> viajes =
                    Factories.services.createTripService().findAll();
            request.setAttribute("listaViajes", viajes);
            Log.debug("Obtenida lista de viajes "
                            + "conteniendo [%d] viajes",
                    viajes.size());
        } catch (BusinessException e) {
            Log.error("Algo ha ocurrido obteniendo la "
                    + "lista de viajes");
        }
        return Navigation.OPCION_EXITO;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}
