package com.sdi.persistence;

import com.sdi.model.User;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.NotPersistedException;

public interface UsersDao {

    	User login(String login,String password);
    	void save(User user) throws AlreadyPersistedException;
	void update(User user) throws NotPersistedException;
	void delete(Long id) throws NotPersistedException;
	User findById(Long id);

}
