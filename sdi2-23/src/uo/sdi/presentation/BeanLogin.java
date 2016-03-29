package uo.sdi.presentation;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.transport.UserDTO;

@ManagedBean(name = "login")
@ViewScoped
public class BeanLogin {
    @ManagedProperty("#{user}")
    private BeanUser user;
    private UserDTO userToBeLogged = new UserDTO();

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

    public void existsUsername(FacesContext context,
	    UIComponent componentToValidate, Object value)
	    throws BusinessException {
	if (Factories.services.createUserService().findByLogin(
		    userToBeLogged) != null) {
		Log.info("El nombre de usuario [%s] no existe", 
			user.getLogin());
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication()
			.getResourceBundle(facesContext, "msgs");
		FacesMessage message = new FacesMessage(
			bundle.getString("username_does_not_exist"));
		throw new ValidatorException(message);
	}
    }

    public void correctPassword(FacesContext context,
	    UIComponent componentToValidate, Object value) throws BusinessException {
	if (!(Factories.services.createUserService()
		.findByLogin(userToBeLogged).getPassword()
		.equals(getPassword()))) {
		Log.info("La contraseña [%s] es incorrecta", 
			user.getPassword());
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication()
			.getResourceBundle(facesContext, "msgs");
		FacesMessage message = new FacesMessage(
			bundle.getString("incorrect_password"));
		throw new ValidatorException(message);
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
	    Log.debug("Ha ocurrido una [%s] validando al usuario [%s]: [%s]", 
		    e.getClass().toString(),
		    userToBeLogged.getLogin(),
		    e.getMessage());
	    return "error";
	}
    }
}
