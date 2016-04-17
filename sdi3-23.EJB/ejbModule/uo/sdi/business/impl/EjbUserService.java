package uo.sdi.business.impl;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.CommandExecutor;
import uo.sdi.business.impl.user.*;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class EjbUserService implements RemoteUserService, LocalUserService {
    @Override
    public UserDTO findById(Long id) throws BusinessException {
        return CommandExecutor.execute(new FindById(id));
    }

    @Override
    public UserDTO findByLoginAndPassword(UserDTO user)
            throws BusinessException {
        return CommandExecutor.execute(new FindByLoginAndPassword(user));
    }

    @Override
    public UserDTO findByLogin(UserDTO user) throws BusinessException {
        return CommandExecutor.execute(new FindByLogin(user));
    }

    @Override
    public UserDTO save(UserDTO user) throws BusinessException {
        return CommandExecutor.execute(new SaveUser(user));
    }

    @Override
    public UserDTO update(UserDTO user) throws BusinessException {
        return CommandExecutor.execute(new UpdateUser(user));
    }

    @Override
    public UserDTO requestSeat(UserDTO user, TripDTO trip)
            throws BusinessException {
        return CommandExecutor.execute(new RequestSeat(user, trip));
    }

    @Override
    public UserDTO cancelApplication(UserDTO user, TripDTO trip)
            throws BusinessException {
        return CommandExecutor.execute(new CancelApplication(user, trip));
    }

    @Override
    public UserDTO delete(UserDTO user) throws BusinessException {
        return CommandExecutor.execute(new DeleteUser(user));
    }

    @Override
    public List<UserDTO> findApplicantsByTripId(Long tripId) throws BusinessException {
        return CommandExecutor.execute(new FindApplicants(tripId));
    }

    @Override
    public UserDTO confirmApplication(Long userId, TripDTO trip) throws BusinessException {
        return CommandExecutor.execute(new ConfirmApplication(userId, trip));
    }

    @Override
    public UserDTO cancelSeat(Long userId, TripDTO trip) throws BusinessException {
        return CommandExecutor.execute(new CancelSeat(userId, trip));
    }

	@Override
	public void restoreDB() throws BusinessException {
		CommandExecutor.execute(new RestoreDB());
	}
}
