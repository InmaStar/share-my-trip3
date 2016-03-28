package uo.sdi.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name = "login")
@ViewScoped
public class BeanLogin {
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void existsUsername(FacesContext context, 
	    UIComponent componentToValidate, Object value){
	//TODO
    }
    
    public void correctPassword(FacesContext context, 
	    UIComponent componentToValidate, Object value){
	//TODO
    }
    
    public String validar(){
	return null; //TODO
    }
}
