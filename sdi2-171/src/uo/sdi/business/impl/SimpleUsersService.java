package uo.sdi.business.impl;

import uo.sdi.business.UsersService;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.impl.classes.user.UsersAlta;
import uo.sdi.model.User;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleUsersService implements UsersService {

    @Override
    public User saveUser(User user) throws EntityAlreadyExistsException {

	return new UsersAlta().save(user);

    }

}
