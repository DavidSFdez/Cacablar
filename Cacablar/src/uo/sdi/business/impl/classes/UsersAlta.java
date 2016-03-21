package uo.sdi.business.impl.classes;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;


public class UsersAlta {

    public void save(User user) throws EntityAlreadyExistsException {
	UserDao dao = Factories.persistence.createUserDao();
	//try {
		dao.save(user);
	//}
	//catch (AlreadyPersistedException ex) {
	//	throw new EntityAlreadyExistsException("Usuario ya existe " + user, ex);
	//}
}
}
