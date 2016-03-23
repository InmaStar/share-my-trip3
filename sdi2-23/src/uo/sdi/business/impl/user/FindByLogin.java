package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.persistence.UserFinder;
import uo.sdi.transport.UserDTO;

import javax.persistence.NoResultException;

public class FindByLogin implements Command<UserDTO> {
    private String login;

    public FindByLogin(UserDTO user) {
        this.login = user.getLogin();
    }

    @Override
    public UserDTO execute() throws BusinessException {
        try {
            return new UserDTO(UserFinder.findByLogin(login));
        } catch (NoResultException e) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }
}
