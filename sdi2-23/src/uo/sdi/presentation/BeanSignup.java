package uo.sdi.presentation;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "signup")
@ViewScoped
public class BeanSignup {

    private String login;
    private String password1;
    private String password2;
    private String nombre;
    private String apellidos;
    private String email;
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void existsUsername(FacesContext context, 
	    UIComponent componentToValidate, Object value){
	//TODO
    }
    
    public void repeatedPassword(FacesContext context, 
	    UIComponent componentToValidate, Object value){
	if(!password1.equals(password2)){
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ResourceBundle bundle = facesContext.getApplication()
			.getResourceBundle(facesContext, "msgs");
	    FacesMessage message = new FacesMessage(
		    bundle.getString("different_passwords"));
	    throw new ValidatorException(message);
	}
    }
    
    public String register(){
	return null; //TODO
    }
}
