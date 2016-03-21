package uo.sdi.business.impl;


import uo.sdi.business.LoginService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.UsersService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public UsersService createUsersService() {
		
	    return new SimpleUsersService();
	}

	@Override
	public LoginService createLoginService() {
	    
	    return new SimpleLoginService();
	}

	

}
