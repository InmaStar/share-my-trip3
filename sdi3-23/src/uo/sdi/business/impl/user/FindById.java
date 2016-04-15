package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserNotFoundException;
import uo.sdi.business.impl.Command;
import uo.sdi.persistence.UserFinder;
import uo.sdi.transport.UserDTO;

import javax.persistence.NoResultException;

public class FindById implements Command<UserDTO> {
    private Long id;

    public FindById(Long id) {
        this.id = id;
    }

    @Override
    public UserDTO execute() throws BusinessException {
        try {
            return new UserDTO(UserFinder.findById(id));
        } catch (NoResultException e) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }
}
