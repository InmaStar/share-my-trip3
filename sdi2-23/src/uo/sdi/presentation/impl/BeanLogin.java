package uo.sdi.presentation.impl;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.presentation.validator.Validations;
import uo.sdi.transport.UserDTO;
import uo.sdi.util.bundle.BundleLoader;

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
    private UserDTO userToBeChecked; // necesario para hacer las validaciones

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
	
	userToBeChecked = new UserDTO(); 
	ResourceBundle bundle = BundleLoader.load("msgs"); 
	String username = (String) value;
	
	Validations.emptyString(
		username, bundle.getString("login_form_login_required"));
	
	userToBeChecked.setLogin(username);
	Validations.nonExistingLogin(userToBeChecked, 
		bundle.getString("username_does_not_exist"));
    }

    public void passwordCorrecta(FacesContext context,
	    UIComponent componentToValidate, Object value)
	    throws BusinessException {

	String password = (String) value;
	ResourceBundle bundle = BundleLoader.load("msgs"); 

	Validations.emptyString(
		password, bundle.getString("login_form_password_required"));
	
	userToBeChecked.setPassword(password);
	try{ 
	    Validations.correctPassword(userToBeChecked,
		    bundle.getString("incorrect_password"));
	} catch(UserNotFoundException e) {}
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
