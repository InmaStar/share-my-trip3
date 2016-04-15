package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.persistence.UserFinder;
import uo.sdi.transport.UserDTO;

import javax.persistence.NoResultException;

public class FindByLoginAndPassword implements Command<UserDTO> {
    private String login;
    private String password;

    public FindByLoginAndPassword(UserDTO user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    @Override
    public UserDTO execute() throws BusinessException {
        try {
            return new UserDTO(UserFinder.findByLoginAndPassword(login,
                    password));
        } catch (NoResultException e) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }
}
