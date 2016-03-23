package uo.sdi.tests;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

import uo.sdi.transport.UserDTO;

public class RegistrarseTest {

    private UserDTO usuario;
    
    @Before
    public void prepare() {
	setBaseUrl("http://localhost:8280/"+ TestResources.CONTEXT_ROOT);
    }

    private void testNavegarAlRegistro() {
	beginAt("/");
	assertLinkPresent("registrarse");
	clickLink("registrarse");
	assertTitleEquals("Registrarse - ShareMyTrip");
	assertTextPresent("Registro");
    }

    @Test
    public void testContraseñasDistintas() {
	usuario = TestResources.USUARIO_NUEVO;
	
	testNavegarAlRegistro();

	// rellenar formulario
	setTextField("nombreUsuario", usuario.getLogin());
	setTextField("password1", usuario.getPassword());
	setTextField("password2", usuario.getPassword() + "*");
	setTextField("nombre", usuario.getName());
	setTextField("apellidos", usuario.getSurname());
	setTextField("email", usuario.getEmail());
	assertElementPresent("submitBtn");
	submit();

	// comprobar que salta el error
	assertTitleEquals("Registrarse - ShareMyTrip");
	assertElementPresent("error");
	assertTextInElement("error", "Las contraseñas no coinciden");
    }

    /*
    @Test
    public void testUsuarioExistente() throws BusinessException {
	usuario = TestResources.USUARIO_EXISTENTE;
	
	testNavegarAlRegistro();

	// rellenar formulario
	setTextField("nombreUsuario", usuario.getLogin());
	setTextField("password1", usuario.getPassword());
	setTextField("password2", usuario.getPassword());
	submit();

	// comprobar que el usuario continua en la misma pagina
	assertTitleEquals("Registrarse - ShareMyTrip");
	assertTextPresent("Registro");

	// comprobar que ha saltado el mensaje de error
	assertElementPresent("error");
	assertTextInElement("error", "Ya existe un usuario con "
		+ "el nombre " + usuario.getLogin());
    }
 */
    /*
    @Test
    public void testRegistroExitoso() throws BusinessException {
	UserDTO usuario = SimuladorDeUsuario.getUsuario();
	
	testNavegarAlRegistro();
	
	//rellenar formulario
	setTextField("nombreUsuario", usuario.getLogin());
	setTextField("password1", usuario.getPassword());
	setTextField("password2", usuario.getPassword());
	setTextField("nombre", usuario.getName());
	setTextField("apellidos", usuario.getSurname());
	setTextField("email", usuario.getEmail());
	submit();
	
	//comprobar que ha avanzado a la pagina siguiente
	assertTitleEquals("Mis viajes - ShareMyTrip");
	assertElementPresent("login"); 
	assertTextInElement("login", usuario.getLogin());
	//assertTextInElement("password1", usuario.getPassword());
	assertTextInElement("name", usuario.getName());
	assertTextInElement("surname", usuario.getSurname());
	assertTextInElement("email", usuario.getEmail());
	
	//borrar usuario para volver al estado previo al test
	SimuladorDeUsuario.borrarUsuario();
    }
    */
}
