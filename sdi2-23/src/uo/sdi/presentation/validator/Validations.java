package uo.sdi.presentation.validator;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.transport.UserDTO;
import alb.util.log.Log;

public class Validations {

    public static void emptyString(String str, String errorMessage) {
	if (str.isEmpty()) {
	    throwValidatorException(errorMessage);
	}
    }

    public static void existsLogin(UserDTO user, String errorMessage)
	    throws BusinessException {
	try {
	    Factories.services.createUserService().findByLogin(user);
	} catch (UserNotFoundException e) {
	    Log.info("El nombre de usuario [%s] no existe", user.getLogin());
	    throwValidatorException(errorMessage);
	}
    }

    public static void correctPassword(UserDTO user, String errorMessage)
	    throws BusinessException {
	UserDTO userFromDB = Factories.services.createUserService()
		.findByLogin(user);
	if (!user.getPassword().equals(userFromDB.getPassword())) {
	    Log.info("La contraseña [%s] es incorrecta", user.getPassword());
	    throwValidatorException(errorMessage);
	}
    }

    public static void repeatedPassword(String password1, String password2,
	    String errorMessage){
	if (!password1.equals(password2)) {
	    throwValidatorException(errorMessage);
	}
    }
    
    private static void throwValidatorException(String errorMessage) {
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		errorMessage, null);
	throw new ValidatorException(message);
    }

}
