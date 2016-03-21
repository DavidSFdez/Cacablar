package uo.sdi.business;

public interface ServicesFactory {
	
	UsersService createUsersService();

	LoginService createLoginService();
	
	TripsService createTripsService();

}
