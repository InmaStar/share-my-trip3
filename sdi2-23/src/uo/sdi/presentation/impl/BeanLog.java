package uo.sdi.presentation.impl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import alb.util.log.Log;
import alb.util.log.LogLevel;
import javax.servlet.ServletContext;

@ManagedBean(eager=true)
@ApplicationScoped
public class BeanLog {

    @PostConstruct
    public void init() {
	String level = ((ServletContext)FacesContext.getCurrentInstance()
		.getExternalContext().getContext())
		.getInitParameter("logLevel");

	switch (level) {
	case "OFF":
	    Log.setLogLevel(LogLevel.OFF);
	    break;
	case "ERROR":
	    Log.setLogLevel(LogLevel.ERROR);
	    break; // Ha ocurrido algo grave que impide la normal ejecución del
		   // programa.
		   // Seguramente el programa tenga que terminar o abortar esa
		   // transacción/petición
	case "WARN":
	    Log.setLogLevel(LogLevel.WARN);
	    break; // Se ha producido algún problema no crítico para el
		   // funcionamiento del programa.
		   // El programa puede continuar pero algo raro ha ocurrido.
	case "INFO":
	    Log.setLogLevel(LogLevel.INFO);
	    break; // Se ha alcanzado algún hito importante en la ejecución del
		   // programa.
	case "DEBUG":
	    Log.setLogLevel(LogLevel.DEBUG);
	    break; // El programador necesita ver en detalle información sobre
		   // el comportamiento de la aplicación.
		   // Este nivel ya no debería estar activado cuando el programa
		   // está en producción.
	case "TRACE":
	    Log.setLogLevel(LogLevel.TRACE);
	    break; // Nivel máximo de detalle de lo que hace la aplicación.
	case "ALL":
	    Log.setLogLevel(LogLevel.ALL);
	    break;
	}
    }
    
}
