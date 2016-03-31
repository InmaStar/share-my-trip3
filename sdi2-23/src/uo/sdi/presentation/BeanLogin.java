package uo.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.transport.UserDTO;

@ManagedBean(name = "login")
@ViewScoped
public class BeanLogin implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @ManagedProperty("#{user}")
    private BeanUser user;
    private UserDTO userToBeLogged;
    private UserDTO userToBeChecked; //necesario para hacer las validaciones

    public BeanUser getAlumno() { return user; }
    public void setAlumno(BeanUser user) {this.user = user;}
    @PostConstruct
    public void init() {     
	user = Factories.beans.createBeanUser();
    }
    
    public BeanLogin() {
	userToBeLogged = new UserDTO();
    }

    public String getUsername() {
	return userToBeLogged.getLogin();
    }

    public void setUsername(String username) {
	userToBeLogged.setLogin(username);
    }

    public String getPassword() {
	return userToBeLogged.getPassword();
    }

    public void setPassword(String password) {
	userToBeLogged.setPassword(password);
    }

    public void existeLogin(FacesContext context,
	    UIComponent componentToValidate, Object value)
	    throws BusinessException {
	String username = (String) value;
	try {

	    userToBeChecked = new UserDTO();
	    userToBeChecked.setLogin(username);
	    userToBeChecked = Factories.services.createUserService()
		    .findByLogin(userToBeChecked);
	} catch (UserNotFoundException e) {
	    Log.info("El nombre de usuario [%s] no existe", username);
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ResourceBundle bundle = facesContext.getApplication()
		    .getResourceBundle(facesContext, "msgs");
	    FacesMessage message = new FacesMessage(
		    bundle.getString("username_does_not_exist"));
	    throw new ValidatorException(message);
	}
    }

    public void passwordCorrecta(FacesContext context,
	    UIComponent componentToValidate, Object value)
	    throws BusinessException {
	
	String password = (String)value;
	
	/*comprobar que se ha cargado el usuario en la validacion de nombre 
	 * de usuario
	 */
	if (!(userToBeChecked.getLogin().equals("") || userToBeChecked
		.getLogin() == null)) {
	    if (!userToBeChecked.getPassword().equals(password)) {
		Log.info("La contraseña [%s] es incorrecta",
			userToBeLogged.getPassword());
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication()
			.getResourceBundle(facesContext, "msgs");
		FacesMessage message = new FacesMessage(
			bundle.getString("incorrect_password"));
		throw new ValidatorException(message);
	    }
	}
    }

    public String validar() {
	try {
	    user.setCurrentUser(Factories.services.createUserService()
		    .findByLogin(userToBeLogged));
	    Log.info("El usuario [%s] se ha validado sin problemas",
		    userToBeLogged.getLogin());
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] validando al usuario [%s]: [%s]", e
		    .getClass().toString(), userToBeLogged.getLogin(), e
		    .getMessage());
	    return "error";
	}
    }
}
