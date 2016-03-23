package uo.sdi.tests;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.transport.UserDTO;

public class ModificarDatosTest {
    
    private UserDTO usuario =  TestResources.USUARIO_EXISTENTE;
    
    @Before
    public void prepare() {
	setBaseUrl("http://localhost:8280/"+ TestResources.CONTEXT_ROOT);
    }

    private void testNavegarAlPerfil() throws BusinessException {
	
	beginAt("/");
	assertLinkPresent("validarse");
	clickLink("validarse");
	assertTitleEquals("Iniciar sesión - ShareMyTrip");
	assertTextPresent("Inicie sesión");

	// identificarse
	setTextField("nombreUsuario", usuario.getLogin());
	setTextField("password", usuario.getPassword());
	submit();

	clickLink("perfil");
	// comprobar que el usuario ha accedido a la pagina del perfil
	assertTitleEquals("Datos del usuario - ShareMyTrip");
	assertElementPresent("login");
	assertTextInElement("login", usuario.getLogin());
    }

    @Test
    public void testContraseñasDistintas() throws BusinessException {
	testNavegarAlPerfil();

	// rellenar formulario
	setTextField("password1", "1234");
	setTextField("password2", "12345");
	submit();

	// comprobar que salta el error
	assertTitleEquals("Datos del usuario - ShareMyTrip");
	assertElementPresent("error");
	assertTextInElement("error", "Las contraseñas no coinciden");
	
    }

    /*
    @Test
    public void testCambiosExitosos() throws BusinessException {
	UserDTO usuarioCambiado;
	UserDTO usuarioOriginal = SimuladorDeUsuario.crearUsuario();

	String nuevaPass = "Nueva contraseña";
	String nuevoNombre = "Nuevo nombre";
	String nuevosApellidos = "Nuevo apellido";
	String nuevoEmail = "nuevo@email.com";

	testNavegarAlPerfil();

	// rellenar formulario
	setTextField("password1", nuevaPass);
	setTextField("password2", nuevaPass);
	setTextField("nombre", nuevoNombre);
	setTextField("apellidos", nuevosApellidos);
	setTextField("email", nuevoEmail);
	submit();

	// comprobar que los datos han sido cambiados
	usuarioCambiado = SimuladorDeUsuario.selectUsuario();
	assertEquals(usuarioCambiado.getLogin(), usuarioCambiado.getLogin());
	assertNotEquals(usuarioCambiado.getName(), usuarioOriginal.getName());
	assertNotEquals(usuarioCambiado.getSurname(),
		usuarioOriginal.getSurname());
	assertNotEquals(usuarioCambiado.getEmail(), usuarioOriginal.getEmail());
	assertNotEquals(usuarioCambiado.getPassword(),
		usuarioOriginal.getPassword());

	// deshacer los cambios
	SimuladorDeUsuario.borrarUsuario();
    }
    */
}
