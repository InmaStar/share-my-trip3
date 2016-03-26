package uo.sdi.entrega1.servlets;

import alb.util.log.Log;
import uo.sdi.entrega1.acciones.Accion;
import uo.sdi.entrega1.servlets.util.Navigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class Controlador extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        String opcion, resultado, jspSiguiente;
        Accion accion;
        String rolAntes, rolDespues;

        try {
            opcion = req.getServletPath().replace("/", "");

            rolAntes = obtenerRolDeSesion(req);

            accion = buscarAccionParaOpcion(rolAntes, opcion);

            resultado = accion.execute(req, res);

            rolDespues = obtenerRolDeSesion(req);


            if (isNavigationForwarded(rolDespues, opcion, resultado)) {
                String url = getNextOption(rolDespues, opcion, resultado);
                req.setAttribute("nextOption", url);
                Log.debug("Redirigiendo a la url: [%s]", url);
                jspSiguiente = Navigation.REDIRECTION_JSP;
            } else {
                jspSiguiente = buscarJSPSegun(rolDespues, opcion, resultado);
            }

        } catch (Exception e) {
            req.getSession().invalidate();

            Log.error("Se ha producido alguna excepción no manejada [%s]", e);

            jspSiguiente = Navigation.ERROR_PAGE;
        }

        req.setAttribute("jspSiguiente", jspSiguiente);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(jspSiguiente);

        dispatcher.forward(req, res);
    }

    private String getNextOption(String rolDespues,
                                 String opcion,
                                 String resultado) {
        return Navigation.getNextOption(rolDespues, opcion, resultado);
    }

    private String obtenerRolDeSesion(HttpServletRequest req) {
        HttpSession sesion = req.getSession();
        if (sesion.getAttribute("user") == null)
            return Navigation.ROL_PUBLICO;
        else
            return Navigation.ROL_REGISTRADO;
    }

    // Obtiene un objeto accion en funci�n de la opci�n
    // enviada desde el navegador
    private Accion buscarAccionParaOpcion(String rol, String opcion) {

        Accion accion = Navigation.getAction(rol, opcion);
        Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]",
                accion, opcion, rol);
        return accion;
    }

    // Obtiene la p�gina JSP a la que habr� que entregar el
    // control el funci�n de la opci�n enviada desde el navegador
    // y el resultado de la ejecuci�n de la acci�n asociada
    private String buscarJSPSegun(String rol, String opcion,
                                  String resultado) {

        String jspSiguiente = Navigation.getJsp(rol, opcion, resultado);
        Log.debug("Elegida página siguiente [%s] para el resultado [%s] tras " +
                        "realizar [%s] con rol [%s]",
                jspSiguiente, resultado, opcion, rol);
        return jspSiguiente;
    }

    private boolean isNavigationForwarded(String rol,
                                          String opcion,
                                          String resultado) {
        return Navigation.isNavigationForwarded(rol, opcion, resultado);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        doGet(req, res);
    }

}