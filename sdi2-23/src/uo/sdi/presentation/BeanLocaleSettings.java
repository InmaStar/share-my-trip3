package uo.sdi.presentation;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "localeSettings")
@SessionScoped
public class BeanLocaleSettings {
    private static final Locale ENGLISH = new Locale("en");
    private static final Locale SPANISH = new Locale("es");
    private Locale locale;
    
    public Locale getLocale() {
	if(locale==null){
	    
	   //obtener locale del navegador
	   locale = FacesContext.getCurrentInstance()
	   	.getExternalContext().getRequestLocale();
	   
	   /*si el idioma del navegador no esta soportado 
	    * se cambia locale a ingles
	    */
	   if(!locale.equals(ENGLISH) || !locale.equals(SPANISH)){
	       locale = ENGLISH; 
	   }
	}
	return (locale);
    }

    public void setSpanish(ActionEvent event) {
	locale = SPANISH;
	FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public void setEnglish(ActionEvent event) {
	locale = ENGLISH;
	FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
