package uo.sdi.business.impl.classes.trip;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.persistence.exception.NotPersistedException;

public class TripsFindById {

    public Trip find(Long idTrip) throws EntityNotFoundException {
	
	try {
	    return Factories.persistence.createTripDao().findById(idTrip);
	} catch (NotPersistedException e) {
	    
	    throw new EntityNotFoundException();
	}
    }

}
