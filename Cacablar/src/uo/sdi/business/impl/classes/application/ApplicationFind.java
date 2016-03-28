package uo.sdi.business.impl.classes.application;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.persistence.exception.NotPersistedException;

public class ApplicationFind {

    public Application find(Long id, Long idUser) throws EntityNotFoundException {
	Long[] ids = {idUser, id};
	
	try {
	    return Factories.persistence.createApplicationDao().findById(ids);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException();
	}
    }
    


}
