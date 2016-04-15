package uo.sdi.business.impl.user;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.UserAlreadyExistsException;
import uo.sdi.business.impl.Command;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;
import uo.sdi.transport.UserDTO;

import javax.persistence.EntityExistsException;

public class SaveUser implements Command<UserDTO> {
    private User user;

    public SaveUser(UserDTO user) {
        this.user = new User(
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getStatus()
        );
    }

    @Override
    public UserDTO execute() throws BusinessException {
        try {
            Jpa.getManager().persist(user);
            return new UserDTO(user);
        } catch (EntityExistsException e) {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }
    }
}
