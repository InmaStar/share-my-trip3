package uo.sdi.tests.entrega1;

import org.junit.Before;
import org.junit.Test;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.types.TripStatus;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;
import uo.sdi.util.DateFormatter;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class RegistrarViajeTest {

    private UserDTO usuario;
    private TripDTO viaje;

    @Before
    public void prepare() {
	setBaseUrl("http://localhost:8280/"+ TestResources.CONTEXT_ROOT);
	usuario = TestResources.USUARIO_EXISTENTE;
	viaje = TestResources.VIAJE_NUEVO;
    }

    private void iniciarSesion() throws BusinessException {
        beginAt("/");
        clickLink("validarse");
        setTextField("nombreUsuario", usuario.getLogin());
        setTextField("password", usuario.getPassword());
        submit();

    }

    public void navegarAlRegistroDeViajes() throws BusinessException {
        iniciarSesion();
        clickLink("nuevoViaje");
    }

    @Test
    public void testRegistrarViaje() throws BusinessException {
	navegarAlRegistroDeViajes();
	
        assertTitleEquals("Registrar viaje - ShareMyTrip");
        assertTextPresent("Nuevo viaje");

        // rellenar formulario
        rellenarFormulario();

        // comprobar que cambio de pagina
        assertTitleEquals("Mis viajes - ShareMyTrip");
        assertTextPresent(viaje.getDeparture().getCity());
        assertTextPresent(viaje.getDestination().getCity());
        assertTextPresent(viaje.getAvailablePax().toString());
        assertTextPresent(viaje.getMaxPax().toString());
        assertTextPresent(TripStatus.OPEN.toString());
    }

    private void rellenarFormulario() {
        String date[];

        // SALIDA
        setTextField("calleSalida", viaje.getDeparture().getAddress());
        setTextField("ciudadSalida", viaje.getDeparture().getCity());
        setTextField("provinciaSalida", viaje.getDeparture().getState());
        setTextField("paisSalida", viaje.getDeparture().getCountry());
        setTextField("codigoPostalSalida", viaje.getDeparture().getZipCode());
        setTextField("latSalida", viaje.getDeparture().getLat().toString());
        setTextField("lonSalida", viaje.getDeparture().getLon().toString());

        //procesar la fecha en dos strings de fecha y hora
        date = DateFormatter.formatDate(viaje.getDepartureDate()).split(" ");
        setTextField("fechaSalida", date[0]);
        setTextField("horaSalida", date[1]);

        // DESTINO
        setTextField("calleDestino", viaje.getDestination().getAddress());
        setTextField("ciudadDestino", viaje.getDestination().getCity());
        setTextField("provinciaDestino", viaje.getDestination().getState());
        setTextField("paisDestino", viaje.getDestination().getCountry());
        setTextField("codigoPostalDestino", viaje.getDestination().getZipCode());
        setTextField("latDestino", viaje.getDestination().getLat().toString());
        setTextField("lonDestino", viaje.getDeparture().getLon().toString());

        //procesar la fecha en dos strings de fecha y hora
        date = DateFormatter.formatDate(viaje.getArrivalDate())
                .split(" ");
        setTextField("fechaLlegada", date[0]);
        setTextField("horaLlegada", date[1]);

        // OTROS
        date = DateFormatter.formatDate(viaje.getClosingDate())
                .split(" ");
        setTextField("fechaLimite", date[0]);
        setTextField("costeEstimado", viaje.getEstimatedCost().toString());
        setTextField("maxPlazas", viaje.getMaxPax().toString());
        setTextField("plazasDisponibles", viaje.getAvailablePax().toString());
        submit();
    }

}
