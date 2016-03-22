package uo.sdi.business.impl.classes.user;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;


public class UsersAlta {

    public User save(User user) throws EntityAlreadyExistsException {
	UserDao dao = Factories.persistence.createUserDao();
	
		try {
		    dao.save(user);
		} catch (AlreadyPersistedException e) {
		    throw new EntityAlreadyExistsException("Usuario ya existe " + user, e);
		}finally{
		    return dao.findByLogin(user.getLogin());
		}
}
}
