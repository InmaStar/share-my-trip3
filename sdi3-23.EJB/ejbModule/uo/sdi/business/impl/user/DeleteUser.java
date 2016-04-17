package uo.sdi.business.impl.user;

import javax.persistence.NoResultException;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;
import uo.sdi.transport.UserDTO;

public class DeleteUser implements Command<UserDTO> {

	private UserDTO user;

	public DeleteUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public UserDTO execute() throws BusinessException {
		try {
			User existingUser = UserFinder.findByLogin(user
					.getLogin());
			Jpa.getManager().remove(existingUser);
			return new UserDTO(existingUser);
		} catch (NoResultException e) {
			throw new UserNotFoundException(
					"No existe ningún usuario con "
							+ "este login");
		}
	}

}
