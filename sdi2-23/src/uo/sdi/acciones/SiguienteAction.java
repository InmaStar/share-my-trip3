package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.servlets.util.Navigation;

/**
 * Accion creada para acceder siempre a la pagina siguiente sin impedimentos
 * @author UO238739
 *
 */
public class SiguienteAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return Navigation.OPCION_EXITO;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
}
