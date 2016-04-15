package uo.sdi.business;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.transport.TripDTO;
import uo.sdi.transport.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id) throws BusinessException;

    UserDTO findByLoginAndPassword(UserDTO user) throws BusinessException;

    UserDTO findByLogin(UserDTO user) throws BusinessException;

    UserDTO save(UserDTO newUser) throws BusinessException;

    UserDTO update(UserDTO user) throws BusinessException;

    UserDTO requestSeat(UserDTO user, TripDTO trip) throws BusinessException;

    UserDTO cancelApplication(UserDTO user, TripDTO trip) throws BusinessException;

    UserDTO delete(UserDTO user) throws BusinessException;

    List<UserDTO> findApplicantsByTripId(Long tripId) throws BusinessException;

    UserDTO confirmApplication(Long userId, TripDTO trip)
            throws BusinessException;

    UserDTO cancelSeat(Long userId, TripDTO trip) throws BusinessException;
    
    void restoreDB() throws BusinessException;
}
