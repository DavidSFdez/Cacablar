package uo.sdi.business.impl;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.application.ApplicationFind;
import uo.sdi.model.Application;

public class SimpleApplicationsService implements ApplicationService{

    @Override
    public Application find(Long idTrip, Long idUser) throws EntityNotFoundException {
	return new ApplicationFind().find(idTrip,idUser);
	
    }

}
