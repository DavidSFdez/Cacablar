package com.sdi.persistence.impl;



import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.UsersDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author alb
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UsersDao createUserDao() {
		return new UserJdbcDAO();
	}

}
