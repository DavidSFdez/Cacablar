package uo.sdi.business;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.model.User;

public interface UsersService {

    User saveUser(User user) throws EntityAlreadyExistsException;

}
