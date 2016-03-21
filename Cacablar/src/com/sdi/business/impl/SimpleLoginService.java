package com.sdi.business.impl;

import com.sdi.business.LoginService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UsersDao;

public class SimpleLoginService implements LoginService {

    @Override
    public User verify(String login, String password) {
	UsersDao dao = Factories.persistence.createUserDao();
	User user = dao.login(login, password);
	return user;
    }
}
