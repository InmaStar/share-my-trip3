package uo.sdi.presentation.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.infrastructure.BusinessFactories;
import uo.sdi.transport.UserDTO;
import alb.util.log.Log;

public class Validations {

	public static void emptyString(String str, String errorMessage) {
		if (str.isEmpty()) {
			throwValidatorException(errorMessage);
		}
	}

	public static void nonExistingLogin(UserDTO user, String errorMessage)
			throws BusinessException {
		try {
			BusinessFactories.services.getUserService().findByLogin(user);
		} catch (UserNotFoundException e) {
			Log.info("El nombre de usuario [%s] no existe", user.getLogin());
			throwValidatorException(errorMessage);
		}
	}

	public static void alreadyExistingLogin(UserDTO user, String errorMessage)
			throws BusinessException {
		try {
		    	BusinessFactories.services.getUserService().findByLogin(user);
			Log.info("El nombre de usuario [%s] ya existe", user.getLogin());
			throwValidatorException(errorMessage);
		} catch (UserNotFoundException e) {
		}
	}

	public static void correctPassword(UserDTO user, String errorMessage)
			throws BusinessException {
		UserDTO userFromDB = BusinessFactories.services.getUserService()
				.findByLogin(user);
		if (!user.getPassword().equals(userFromDB.getPassword())) {
			Log.info("La contraseña [%s] es incorrecta", user.getPassword());
			throwValidatorException(errorMessage);
		}
	}

	public static void repeatedPassword(String password1, String password2,
			String errorMessage) {
		if (!password1.equals(password2)) {
			throwValidatorException(errorMessage);
		}
	}

	public static void dateBefore(Date date, Date maxDate, String errorMessage) {
		if (!date.before(maxDate)) {
			throwValidatorException(errorMessage);
		}
	}

	public static void dateAfter(Date date, Date minDate, String errorMessage) {
		if (!date.after(minDate)) {
			throwValidatorException(errorMessage);
		}
	}

	private static void throwValidatorException(String errorMessage) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				errorMessage, null);
		throw new ValidatorException(message);
	}

}
