package com.sdi.business;

public interface ServicesFactory {
	
	UsersService createUsersService();

	LoginService createLoginService();

}
