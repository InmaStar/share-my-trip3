package uo.sdi.tests.entrega1;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.transport.UserDTO;

public class SolicitarPlazaTest {

    private UserDTO usuario;
    
    @Before
    public void prepare() {
	setBaseUrl("http://localhost:8280/"+ TestResources.CONTEXT_ROOT);
	usuario = TestResources.USUARIO_EXISTENTE;
    }

    private void iniciarSesion() throws BusinessException {
        beginAt("/");
        clickLink("validarse");
        setTextField("nombreUsuario", usuario.getLogin());
        setTextField("password", usuario.getPassword());
        submit();
    }
    
    @Test
    public void testSolicitarPlaza() throws BusinessException {
	iniciarSesion();
	
        assertTitleEquals("Mis viajes - ShareMyTrip");
        
        //viaje de id nº100 precargado en DB
        assertElementPresent("solicitarPlaza100"); 
        assertTextInElement("solicitarPlaza100", "Solicitar plaza");
        
        //solicitar plaza
        clickLink("solicitarPlaza100");
        
        //comprobar pagina
        assertTitleEquals("Mis viajes - ShareMyTrip");
        assertElementNotPresent("solicitarPlaza100"); 
        assertElementPresent("cancelarPlaza100");
        
        testCancelarPlaza();
    }

    public void testCancelarPlaza() throws BusinessException {
	clickLink("cancelarPlaza100");
	assertTitleEquals("Mis viajes - ShareMyTrip");
	assertElementPresent("solicitarPlaza100"); 
        assertElementNotPresent("cancelarPlaza100");
    }
    
}
