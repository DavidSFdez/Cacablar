package uo.sdi.business.impl.classes.trip;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.persistence.exception.NotPersistedException;

public class TripsUpdate {

    public void update(Trip trip, Long idUser) throws EntityNotFoundException {
	try {
	    Factories.persistence.createTripDao().update(trip);
	} catch (NotPersistedException ex) {
	    throw new EntityNotFoundException();
	}
    }

}
