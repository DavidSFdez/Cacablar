package com.sdi.business;



import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.User;


public interface UsersService {

    	User findById(Long id) throws EntityNotFoundException;
	void saveUser(User user) throws EntityAlreadyExistsException;
	void updateUser(User user) throws EntityNotFoundException;
	void deleteUser(Long id) throws EntityNotFoundException;
}
