package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.application.ApplicationsFind;
import uo.sdi.business.impl.classes.application.ApplicationsGetToUpdate;
import uo.sdi.business.impl.classes.application.ApplicationsRemove;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;

public class SimpleApplicationsService implements ApplicationService{

    @Override
    public Application find(Long idTrip, Long idUser) throws EntityNotFoundException {
	return new ApplicationsFind().find(idTrip,idUser);
	
    }

    @Override
    public void remove(Long idUser, Long idTrip) throws EntityNotFoundException {
	new ApplicationsRemove().remove(idUser,idTrip);
	
    }

    @Override
    public List<Application> getToUpdate() {
	
	return new ApplicationsGetToUpdate().find();
    }

}
