package uo.sdi.tests.entrega1;
import java.util.Calendar;

import uo.sdi.model.types.TripStatus;
import uo.sdi.transport.AddressPointDTO;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;


public class TestResources {

    public static final UserDTO USUARIO_EXISTENTE = new UserDTO("test", "test",
            "Name", "Surname", "test@test.com");
    
    public static final UserDTO USUARIO_NUEVO = new UserDTO("nuevo", "nuevo",
            "Name", "Surname", "test@test.com");
    
    public static final String CONTEXT_ROOT = "MartinezInmaculada-VelascoLuisEmilio";
    
    public static final TripDTO VIAJE_NUEVO = createTrip();
    
    private static final TripDTO createTrip(){
	TripDTO viaje = new TripDTO();
	Calendar calendar = Calendar.getInstance();
        AddressPointDTO departure = new AddressPointDTO();
        AddressPointDTO destination = new AddressPointDTO();
        
	departure.setAddress("calleSalida");
        departure.setCity("ciudadSalida");
        departure.setCountry("paisSalida");
        departure.setState("provinciaSalida");
        departure.setZipCode("codigoPostalSalida");
        departure.setLat(0.0);
        departure.setLon(0.0);

        viaje.setDepartureDate(calendar.getTime());

        // DESTINO
        destination.setAddress("calleDestino");
        destination.setCity("ciudadDestino");
        destination.setCountry("paisDestino");
        destination.setState("provinciaDestino");
        destination.setZipCode("codigoPostalDestino");
        destination.setLat(0.0);
        destination.setLon(0.0);

        viaje.setArrivalDate(calendar.getTime());

        // OTROS
        viaje.setClosingDate(calendar.getTime());

        viaje.setDeparture(departure);
        viaje.setDestination(destination);
        viaje.setEstimatedCost(10.0);
        viaje.setAvailablePax(5);
        viaje.setMaxPax(5);
        viaje.setStatus(TripStatus.OPEN);
        viaje.setComments("comentarios");
	
	return viaje;
    }
}
