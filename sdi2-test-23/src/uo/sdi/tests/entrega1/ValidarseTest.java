package uo.sdi.tests.entrega1;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.transport.UserDTO;

public class ValidarseTest {

    private UserDTO usuario;
    
    @Before
    public void prepare() {
	setBaseUrl("http://localhost:8280/"+ TestResources.CONTEXT_ROOT);
    }

    private void testNavegarAlInicioDeSesion() {
	beginAt("/");
	assertLinkPresent("validarse");
	clickLink("validarse");
	assertTitleEquals("Iniciar sesión - ShareMyTrip");
	assertTextPresent("Inicie sesión");
    }

    @Test
    public void testContraseñaIncorrecta() throws BusinessException {
	usuario = TestResources.USUARIO_EXISTENTE;

	testNavegarAlInicioDeSesion();

	// rellenar formulario
	setTextField("nombreUsuario", usuario.getLogin());
	setTextField("password", usuario.getPassword() + "*");
	submit();

	// comprobar que salta el error
	assertTitleEquals("Iniciar sesión - ShareMyTrip");
	assertElementPresent("error");
	assertTextInElement("error", "La contraseña es incorrecta");
    }

    @Test
    public void testValidacionExitosa() throws BusinessException {
	usuario = TestResources.USUARIO_EXISTENTE;

	testNavegarAlInicioDeSesion();

	// rellenar formulario
	setTextField("nombreUsuario", usuario.getLogin());
	setTextField("password", usuario.getPassword());
	submit();

	/*
	 * comprobar que el usuario ha accedido correctamente y esta en la
	 * pagina siguiente
	 */
	assertTitleEquals("Mis viajes - ShareMyTrip");
    }

}
