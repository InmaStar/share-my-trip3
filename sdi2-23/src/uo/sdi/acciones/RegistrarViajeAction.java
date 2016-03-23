package uo.sdi.acciones;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.types.TripStatus;
import uo.sdi.servlets.util.Navigation;
import uo.sdi.transport.AddressPointDTO;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;
import uo.sdi.util.DateFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

public class RegistrarViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO promotor = (UserDTO) session.getAttribute("user");
        try {
            TripDTO viaje = leerFormulario(request);
            viaje = Factories.services.createTripService()
                    .insert(viaje, promotor);
            Log.info("El promotor [%s] ha registrado "
                            + "un nuevo viaje [%d] con éxito",
                    promotor.getLogin(),
                    viaje.getId());
            return Navigation.OPCION_EXITO;
        } catch (BusinessException e) {
            Log.error("Ha habido un problema registrando el nuevo viaje");
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

    static TripDTO leerFormulario(HttpServletRequest request)
            throws ParseException {
        String date;
        TripDTO viaje = new TripDTO();
        AddressPointDTO departure = new AddressPointDTO();
        AddressPointDTO destination = new AddressPointDTO();

        // SALIDA
        departure.setAddress(request.getParameter("calleSalida"));
        departure.setCity(request.getParameter("ciudadSalida"));
        departure.setCountry(request.getParameter("paisSalida"));
        departure.setState(request.getParameter("provinciaSalida"));
        departure.setZipCode(request.getParameter("codigoPostalSalida"));
        departure.setLat(Double.parseDouble(request.getParameter("latSalida")));
        departure.setLon(Double.parseDouble(request.getParameter("lonSalida")));

        date = request.getParameter("fechaSalida") + " "
                + request.getParameter("horaSalida");
        viaje.setDepartureDate(DateFormatter.parseDate(date));

        // DESTINO
        destination.setAddress(request.getParameter("calleDestino"));
        destination.setCity(request.getParameter("ciudadDestino"));
        destination.setCountry(request.getParameter("paisDestino"));
        destination.setState(request.getParameter("provinciaDestino"));
        destination.setZipCode(request.getParameter("codigoPostalDestino"));
        destination.setLat(
                Double.parseDouble(request.getParameter("latDestino")));
        destination.setLon(
                Double.parseDouble(request.getParameter("lonDestino")));

        date = request.getParameter("fechaLlegada") + " "
                + request.getParameter("horaLlegada");
        viaje.setArrivalDate(DateFormatter.parseDate(date));

        // OTROS
        date = request.getParameter("fechaLimite") + " 00:00";
        viaje.setClosingDate(DateFormatter.parseDate(date));

        viaje.setDeparture(departure);
        viaje.setDestination(destination);
        viaje.setEstimatedCost(Double.parseDouble(request
                .getParameter("costeEstimado")));
        viaje.setAvailablePax(Integer.parseInt(request
                .getParameter("plazasDisponibles")));
        viaje.setMaxPax(Integer.parseInt(request.getParameter("maxPlazas")));
        viaje.setStatus(TripStatus.OPEN);
        viaje.setComments(request.getParameter("comentarios"));

        return viaje;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
