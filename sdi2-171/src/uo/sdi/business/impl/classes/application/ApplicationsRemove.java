package uo.sdi.business.impl.classes.application;

import alb.util.log.Log;
import uo.sdi.infrastructure.Factories;
import uo.sdi.persistence.exception.NotPersistedException;

public class ApplicationsRemove {

    public void remove(Long idUser, Long idTrip) {
	Long[] ids = { idUser, idTrip };

	try {
	    Factories.persistence.createApplicationDao().delete(ids);
	} catch (NotPersistedException e) {
	    Log.warn("Not such application.");
	}

    }

}
