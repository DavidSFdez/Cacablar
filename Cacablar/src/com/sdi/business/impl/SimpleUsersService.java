package com.sdi.business.impl;

import com.sdi.business.UsersService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.UsersAlta;
import com.sdi.model.User;


/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleUsersService implements UsersService {


    @Override
    public User findById(Long id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void saveUser(User user) throws EntityAlreadyExistsException {
	
	new UsersAlta().save(user);
	
    }

    @Override
    public void updateUser(User user) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	
    }

	
}
