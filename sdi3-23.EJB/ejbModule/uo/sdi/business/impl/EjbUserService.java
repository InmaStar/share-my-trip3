package uo.sdi.business.impl;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.user.*;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class EjbUserService implements RemoteUserService, LocalUserService {
    @Override
    public UserDTO findById(Long id) throws BusinessException {
        return new FindById(id).execute();
    }

    @Override
    public UserDTO findByLoginAndPassword(UserDTO user)
            throws BusinessException {
        return new FindByLoginAndPassword(user).execute();
    }

    @Override
    public UserDTO findByLogin(UserDTO user) throws BusinessException {
        return new FindByLogin(user).execute();
    }

    @Override
    public UserDTO save(UserDTO user) throws BusinessException {
        return new SaveUser(user).execute();
    }

    @Override
    public UserDTO update(UserDTO user) throws BusinessException {
        return new UpdateUser(user).execute();
    }

    @Override
    public UserDTO requestSeat(UserDTO user, TripDTO trip)
            throws BusinessException {
        return new RequestSeat(user, trip).execute();
    }

    @Override
    public UserDTO cancelApplication(UserDTO user, TripDTO trip)
            throws BusinessException {
        return new CancelApplication(user, trip).execute();
    }

    @Override
    public UserDTO delete(UserDTO user) throws BusinessException {
        return new DeleteUser(user).execute();
    }

    @Override
    public List<UserDTO> findApplicantsByTripId(Long tripId) throws BusinessException {
        return new FindApplicants(tripId).execute();
    }

    @Override
    public UserDTO confirmApplication(Long userId, TripDTO trip) throws BusinessException {
        return new ConfirmApplication(userId, trip).execute();
    }

    @Override
    public UserDTO cancelSeat(Long userId, TripDTO trip) throws BusinessException {
        return new CancelSeat(userId, trip).execute();
    }

	@Override
	public void restoreDB() throws BusinessException {
		new RestoreDB();
	}
}
