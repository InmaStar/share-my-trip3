package uo.sdi.presentation.impl;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.transport.UserDTO;

@ManagedBean(name = "signup")
@ViewScoped
public class BeanSignup implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UserDTO userToBeRegistered;
    private String password2;

    public BeanSignup() {
	userToBeRegistered = new UserDTO();
    }

    public String getLogin() {
	return userToBeRegistered.getLogin();
    }

    public void setLogin(String login) {
	userToBeRegistered.setLogin(login);
    }

    public String getPassword1() {
	return userToBeRegistered.getPassword();
    }

    public void setPassword1(String password1) {
	userToBeRegistered.setPassword(password1);
    }

    public String getPassword2() {
	return password2;
    }

    public void setPassword2(String password2) {
	this.password2 = password2;
    }

    public String getNombre() {
	return userToBeRegistered.getName();
    }

    public void setNombre(String nombre) {
	userToBeRegistered.setName(nombre);
    }

    public String getApellidos() {
	return userToBeRegistered.getSurname();
    }

    public void setApellidos(String apellidos) {
	userToBeRegistered.setSurname(apellidos);
    }

    public String getEmail() {
	return userToBeRegistered.getEmail();
    }

    public void setEmail(String email) {
	userToBeRegistered.setEmail(email);
    }

    public void existeLogin(FacesContext context,
	    UIComponent componentToValidate, Object value)
	    throws BusinessException {
	try {
	    Factories.services.createUserService()
	    	.findByLogin(userToBeRegistered);
	} catch (UserNotFoundException e) {
	    Log.info("El nombre de usuario [%s] no existe", 
		    userToBeRegistered.getLogin());
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ResourceBundle bundle = facesContext.getApplication()
		    .getResourceBundle(facesContext, "msgs");
	    FacesMessage message = new FacesMessage(
		    bundle.getString("username_does_not_exist"));
	    throw new ValidatorException(message);
	}
    }

    public void passwordRepetida(FacesContext context,
	    UIComponent componentToValidate, Object value) {
	if (!userToBeRegistered.getPassword().equals(password2)) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ResourceBundle bundle = facesContext.getApplication()
		    .getResourceBundle(facesContext, "msgs");
	    FacesMessage message = new FacesMessage(
		    bundle.getString("different_passwords"));
	    throw new ValidatorException(message);
	}
    }

    public String registrar() {
	try {
	    Factories.services.createUserService().save(userToBeRegistered);
	    Log.info("Se ha registrado el usuario [%s]",
		    userToBeRegistered.getLogin());
	    return "exito";
	} catch (Exception e) {
	    Log.debug("Ha ocurrido una [%s] registrando al usuario [%s]: [%s]",
		    e.getClass().toString(), userToBeRegistered.getLogin(),
		    e.getMessage());
	    return "error";
	}
    }
}
